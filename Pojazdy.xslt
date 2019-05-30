<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Ewidencja Pojazdów</h2>
<table style="width:100%" border="1" bordercolor="#555555" bgcolor="#EEEEEE">
  <tr bgcolor="#AAAAAA">
    <xsl:for-each select="pojazdy/pojazd" >
      <td><b><xsl:value-of select="marka"/></b></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>typ: <xsl:value-of select="@typ"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>model: <xsl:value-of select="model"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>rok produkcji: <xsl:value-of select="rok_produkcji"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>stan licznika: <xsl:value-of select="stan_licznika"/><xsl:value-of select="stan_licznika/@jednostka"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>numer rejestracyjny: <xsl:value-of select="numer_rejestracyjny"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>numer vin: <xsl:value-of select="numer_vin"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>masa wlasna <xsl:value-of select="masa_wlasna"/><xsl:value-of select="masa_wlasna/@jednostka"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>pojemnosc: <b><xsl:value-of select="pojemnosc"/></b><xsl:value-of select="pojemnosc/@jednostka"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd" >
      <td>kolor: <xsl:value-of select="kolor"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd">
      <td>oc:
        <ul type="none">
          <li>do: <xsl:value-of select="ubezpieczenie/oc/do"/></li>
          <li>koszt: <xsl:value-of select="ubezpieczenie/oc/koszt"/><xsl:value-of select="ubezpieczenie/oc/koszt/@waluta"/></li>
          <li>nazwa: <xsl:value-of select="ubezpieczenie/oc/nazwa_ubezpieczyciela"/></li>
        </ul>
      </td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd">
      <td><xsl:if test="ubezpieczenie/ac">ac: 
        <ul type="none">
          <li>do: <xsl:value-of select="ubezpieczenie/ac/do"/></li>
          <li>koszt: <xsl:value-of select="ubezpieczenie/ac/koszt"/><xsl:value-of select="ubezpieczenie/ac/koszt/@waluta"/></li>
          <li>kwota: <xsl:value-of select="ubezpieczenie/ac/kwota"/><xsl:value-of select="ubezpieczenie/ac/kwota/@waluta"/></li>
          <li>nazwa: <xsl:value-of select="ubezpieczenie/ac/nazwa_ubezpieczyciela"/></li>
        </ul>
      </xsl:if>
      </td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd">
      <td><xsl:if test="ubezpieczenie/assistance">
        assistance: 
        <ul type="none">
          <li>do: <xsl:value-of select="ubezpieczenie/assistance/do"/></li>
          <li>koszt: <xsl:value-of select="ubezpieczenie/assistance/koszt"/><xsl:value-of select="ubezpieczenie/assistance/koszt/@waluta"/></li>
          <li>do ilu km: <xsl:value-of select="ubezpieczenie/assistance/do_ilu_km"/></li>
          <li>nazwa: <xsl:value-of select="ubezpieczenie/assistance/nazwa_ubezpieczyciela"/></li>
        </ul>
      </xsl:if>
      </td>
    </xsl:for-each>
</tr>
<tr align="center">
    <xsl:for-each select="pojazdy/pojazd">
    <td bgcolor="#5d85d5">
		Suma ubezpieczenia:<br/>
		<xsl:variable name="oc" select="ubezpieczenie/oc/koszt"/>

		<xsl:variable name="ac">
			<xsl:choose>
				<xsl:when test="ubezpieczenie/ac">
					<xsl:value-of select="ubezpieczenie/ac/koszt"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="0"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<xsl:variable name="assistance">
			<xsl:choose>
				<xsl:when test="ubezpieczenie/assistance">
					<xsl:value-of select="ubezpieczenie/assistance/koszt"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="0"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>

		<b><xsl:value-of select="$assistance + $ac +$oc"/></b>
	  </td>
    </xsl:for-each>
  </tr>
</table>
  <ul>
    <xsl:for-each select="pojazdy/typy_pojazdow/typ">
    <li>
      <t><xsl:value-of select="."/></t>
    </li>
    </xsl:for-each>
  </ul>
</body>
</html>
</xsl:template>
</xsl:stylesheet>