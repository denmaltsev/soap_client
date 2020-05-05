# soap_client
Sample soap client with spring boot

### Notes
For enable use java 11 and above, add this into pom.xml:
```xml
	<profiles>
		<profile>
			<id>java11</id>
			<activation>
				<jdk>[11,)</jdk>
			</activation>

			<dependencies>
				<dependency>
					<groupId>org.glassfish.jaxb</groupId>
					<artifactId>jaxb-runtime</artifactId>
				</dependency>
			</dependencies>
		</profile>
	</profiles>
```
