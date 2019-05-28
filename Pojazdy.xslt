<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
<body>
  <h2>Ewidencja Pojazdów</h2>
<table style="width:100%" border="1" bordercolor="#555555" bgcolor="#EEEEEE">
  <tr>
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
      <td>oc: <xsl:value-of select="ubezpieczenie/oc"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd">
            <td>ac: <xsl:value-of select="ubezpieczenie/ac"/></td>
    </xsl:for-each>
  </tr>
  <tr>
    <xsl:for-each select="pojazdy/pojazd">
            <td>assistance: <xsl:value-of select="ubezpieczenie/assistance"/></td>
    </xsl:for-each>
  </tr>
</table>
  <ul>
    <xsl:for-each select="pojazdy/pojazd">
    <li>
      <t><xsl:value-of select="marka"/></t>
      <t><xsl:value-of select="model"/></t>
    </li>
    </xsl:for-each>
  </ol>
</body>
</html>
</xsl:template>
</xsl:stylesheet>