<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set value="#request['AFInfo']" name="af" id="af" />
{success:true, 'afId':'
<s:property value="#af.afId" />
', 'ad':'
<s:date name="#af.ad" format="yyyy-MM-dd HH:MM:SS" nice="false" />
', 'tel':'
<s:property value="#af.tel" />
', 'afNo':'
<s:property value="#af.afNo" />
'}
