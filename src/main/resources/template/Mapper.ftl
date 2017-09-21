<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
  <resultMap id="BaseResultMap" type="${entityType}">
${resultMap}
  </resultMap>
  
  <!-- 基本列 -->
  <sql id="Base_Column_List">
    ${baseColumn}
  </sql>
  
  <!-- 查询所有 -->
  <select id="query${entityName}List" parameterType="java.util.Map" resultType="hashmap" >
    SELECT * FROM ${tableName} WHERE 1=1 
    ${findListConditon}
  </select>
  
  <!-- 分页查询 -->
  <select id="query${entityName}ListPage" parameterType="java.util.Map" resultType="hashmap" >
    SELECT * FROM ${tableName} WHERE 1=1 
    ${findListConditon}
  </select>
  
  <!-- 单个查询 -->
  <select id="query${entityName}First" parameterType="${entityType}" resultMap="BaseResultMap">
    SELECT
    <include refid="Base_Column_List" />
    FROM ${tableName} 
    WHERE 1=1 ${keyProps}
  </select>
    
  <!-- 单个插入 -->
  <insert id="insert${entityName}" parameterType="${entityType}" >
    insert into ${tableName}
    <trim prefix="(" suffix=")" suffixOverrides=",">
${insertIfColumns}
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
${insertIfProps}
    </trim>
  </insert>
    
  <!-- 单个更新 -->
  <update id="update${entityName}" parameterType="${entityType}">
    update ${tableName}
    <set>
${updateColProps}
    </set>
    WHERE 1=1 ${keyProps}
  </update>
  
  <!-- 删除 -->
  <update id="delete${entityName}" parameterType="java.util.Map" >
    DELETE FROM S_USR 
     WHERE 1=1 ${keyProps}
  </update>
    
  <!-- 批量新增 -->
  <insert id="insert${entityName}Batch" parameterType="java.util.List">
    INSERT INTO ${tableName}
    (${insertBatchColumns})
    VALUES
    <foreach collection="list" item="item" index="index" separator=",">
       (${insertBatchProps})
    </foreach>
  </insert>
    
  <!-- 批量更新 -->
  <update id="update${entityName}Batch" parameterType="java.util.List">
    <foreach collection="list" item="item" index="index" open="" close="" separator=";">  
        UPDATE ${tableName}
        <set>
${updateBatchColProps}
        </set>
        WHERE 1=1 ${keyProps}
    </foreach> 
  </update>
  
</mapper>