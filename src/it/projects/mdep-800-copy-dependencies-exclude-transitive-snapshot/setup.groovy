/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.jar.JarOutputStream

import org.codehaus.plexus.util.FileUtils

def repositoryPath = 'org/apache/maven/its/dependency/mdep800/dependency/1.0-SNAPSHOT'
FileUtils.deleteDirectory(new File(localRepositoryPath, repositoryPath))

def artifactDirectory = new File(basedir, "repo/${repositoryPath}")
assert artifactDirectory.mkdirs() || artifactDirectory.isDirectory()

def timestampedVersion = '1.0-20220318.120000-1'
new JarOutputStream(new FileOutputStream(new File(artifactDirectory, "dependency-${timestampedVersion}.jar"))).close()

new File(artifactDirectory, "dependency-${timestampedVersion}.pom").text = '''\
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.apache.maven.its.dependency.mdep800</groupId>
  <artifactId>dependency</artifactId>
  <version>1.0-SNAPSHOT</version>
</project>
'''

new File(artifactDirectory, 'maven-metadata.xml').text = '''\
<?xml version="1.0" encoding="UTF-8"?>
<metadata>
  <groupId>org.apache.maven.its.dependency.mdep800</groupId>
  <artifactId>dependency</artifactId>
  <version>1.0-SNAPSHOT</version>
  <versioning>
    <snapshot>
      <timestamp>20220318.120000</timestamp>
      <buildNumber>1</buildNumber>
    </snapshot>
    <lastUpdated>20220318120000</lastUpdated>
    <snapshotVersions>
      <snapshotVersion>
        <extension>jar</extension>
        <value>1.0-20220318.120000-1</value>
        <updated>20220318120000</updated>
      </snapshotVersion>
      <snapshotVersion>
        <extension>pom</extension>
        <value>1.0-20220318.120000-1</value>
        <updated>20220318120000</updated>
      </snapshotVersion>
    </snapshotVersions>
  </versioning>
</metadata>
'''

return true
