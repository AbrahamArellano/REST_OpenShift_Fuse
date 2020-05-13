# REST_OpenShift_Fuse
Camel REST application deployed on OpenShift using Fuse

## Prerequisites
Please read and follow the instructions on README_OCP_Preparation.md

## Create project (only as a reference - do not use this step)
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate \
  -DarchetypeCatalog=https://maven.repository.redhat.com/ga/io/fabric8/archetypes/archetypes-catalog/2.2.0.fuse-sb2-760038-redhat-00001/archetypes-catalog-2.2.0.fuse-sb2-760038-redhat-00001-archetype-catalog.xml \
  -DarchetypeGroupId=org.jboss.fuse.fis.archetypes \
  -DarchetypeArtifactId=spring-boot-camel-rest-sql-archetype \
  -DarchetypeVersion=2.2.0.fuse-sb2-760038-redhat-00001
  
## Deploy OpenShift
oc project redhat-router

cd /home/aarellan/workspaces/BMW/REST_OpenShift_Fuse

mvn fabric8:deploy -Popenshift

## Undeploy OpenShift
mvn fabric8:undeploy -Popenshift


		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-undertow</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>		
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
			<exclusions>
				<exclusion>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</exclusion>
			</exclusions>
		</dependency>		

		<dependency>
			<groupId>org.apache.camel</groupId>
			<artifactId>camel-servlet-starter</artifactId>
		</dependency>		