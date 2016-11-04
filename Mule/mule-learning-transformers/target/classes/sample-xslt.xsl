<xsl:stylesheet version="3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />
	<xsl:param name="name" />
	<xsl:param name="age" />
	<xsl:template match="/">
		<output>
			<name>
				<xsl:value-of select="$name" />
			</name>
			<age>
				<xsl:value-of select="$age" />
			</age>
			<dob>
				<xsl:value-of select="//dob" />
			</dob>
		</output>
	</xsl:template>
</xsl:stylesheet>