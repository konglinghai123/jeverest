# jeverest
In short, the Everest Framework is designed to ease the creation, formatting, and transmission of HL7v3 structures with remote systems. 

# Home

<div class="wikidoc">
<p>In short, the Everest Framework is designed to ease the creation, formatting, and transmission of HL7v3 structures with remote systems.&nbsp;</p>
<p>The &quot;framework&quot; provides a series of consistent, well documented components that, when used together, provide a flexible mechanism for supporting HL7v3 standards within application. Through a combination of automatically generated code and carefully constructed
 handwritten modules, Everest has the ability <a>to</a> serialize, validate, and transmit structures. Everest comes bundled with basic serialization capabilities for:</p>
<ul>
<li>HL7 Clinical Document Architecture r2 </li><li>HL7v3 Messaging </li><li>Normative Edition 2008 </li><li>Normative Edition 2010 </li><li>pan-Canadian Messaging Specifications </li><li>R02.04.01 </li><li>R02.04.02 </li><li>R02.04.03 </li></ul>
<p><br>
The serialization assemblies bundled with Everest represent the structures contained within the MIF files bundled with documentation (where license permits), functionality (validation, casting, etc.) and structure meta-data. Additional standards or documentation
 for the bundled DLLs can be generated by processing Model Interchange Format (MIF) files (version 2.1.2, 2.1.3, 2.1.4, 2.1.5 and 2.1.6) using either the GPMR or GPMR Wizard tools bundled with the framework. The following (additional) standards are known to
 work with Everest but are not included:</p>
<ul>
<li>pan-Canadian CeRX 4.3 messaging </li><li>Universal Normative Edition 2009 </li></ul>
<p><br>
Everest currently supports serializing structures to/from the following formats:</p>
<ul>
<li>XML ITS 1.0 </li><li>HL7v3 XML Data Types R1 (UV and CA extensions) </li><li>HL7v3 XML Data Types R2 </li><li>Binary format </li></ul>
<p><br>
Everest currently supports transporting structures to/from other systems using the following connectors:</p>
<ul>
<li>Windows Communication Foundation (Server/Client mode) (basicHttpBinding, wsHttpBinding, ws2007HttpBinding, netTcpBinding)
</li><li>File Systems (Server/Client) </li><li>Msmq (Publish only) </li></ul>
<p><br>
The pillars of Everest are:</p>
<ul>
<li><strong>Intuitiveness: </strong>All components within Everest are designed to be intuitive to developers. Great care has been taken to reduce the complexity of the Framework and allow developers to focus on HL7v3 messaging
</li><li><strong>Standards Compliance: </strong>Being a standards-based framework, one of the foundational pieces is standards compliance. The Everest framework is more than just a serialization engine; it will generate messages, transport them, and validate instances
 in a standards-compliant manner. </li><li><strong>Quality:</strong> Everest code is held to the highest standard of quality in terms of regression testing and documentation. All changes made to the framework are reviewed for their quality and are subject to over 8,000 tests.
</li><li><strong>Performance</strong>: Everest has been designed with long-term performance in mind. Many of the methods within Everest (especially formatting) have the ability to &quot;learn&quot; and become faster the more they are used.
</li><li><strong>Flexibility</strong>: Everest has been designed to be flexible in the manner that allows it to support new HL7v3 standards.
</li></ul>
<h6>Architecture</h6>
<p>The MARC-HI Everest Framework is modeled using a very loosely coupled architecture. This design allows application developers to program against one set of HL7v3 models, and serialize/de-serialize to many different Implementable Technology Specification
 (ITS) formats. The MARC-HI Everest Framework also allows applications to consume or produce these models using a wide array of transport mechanisms.</p>
<p>This flexible architecture ensures that the internal canonical data of your application is safely insulated from changes in the HL7v3 ITS, or transport specifications.</p>
<p><img src="http://te.marc-hi.ca/projects/ev/images/framework-diagram.jpg" alt="framework diagram"></p>
<h2>Continuous Integration Status:</h2>
<p>You can view the CI build results of Everest here: <a href="http://ci-services.fyfesoftware.ca:8081/job/Everest%20Community%20Build/">
http://ci-services.fyfesoftware.ca:8081/job/Everest%20Community%20Build/</a></p>
<p>&nbsp;</p>
</div><div class="ClearBoth"></div>

# Collection Shortcuts

<div class="wikidoc">
<p>The goal of the Everest framework data types is to provide functionality that allows developers to easily construct and interact with the HL7v3 data types. In previous versions of the Everest Framework, creating collections could be difficult. Consider the
 AD data type which is nothing more than a collection of ADXP components. In previous versions of Everest, creating this structure would look something like this:</p>
<pre><br>AD homeAddress = new AD(<br>new SET&lt;CS&lt;PostalAddressUse&gt;&gt;() <br>&nbsp; {<br>PostalAddressUse.Alphabetic, <br>PostalAddressUse.Direct <br>&nbsp; },<br>new ADXP[] {<br>new ADXP(&quot;123 Main Street&quot;, AddressPartType.StreetAddressLine),<br>new ADXP(&quot;West&quot;, AddressPartType.Direction),<br>new ADXP(&quot;Hamilton&quot;, AddressPartType.City),<br>new ADXP(&quot;Ontario&quot;, AddressPartType.State),<br>new ADXP(&quot;Canada&quot;, AddressPartType.Country)<br>}<br>);</pre>
<p><br>
This code can be quite large and is difficult to track if not styled properly (indentation is the key here). So, to make the construction of sets a little easier, we've added static &quot;creator&quot; methods on each of the collection data types. They are used as follows:</p>
<pre><br>AD homeAddress = AD.CreateAD(<br>SET&lt;PostalAddressUse&gt;.CreateSET(<br>PostalAddressUse.Alphabetic,<br>PostalAddressUse.Direct<br>),<br>new ADXP(&quot;123 Main Street&quot;, AddressPartType.City),<br>new ADXP(&quot;West&quot;, AddressPartType.Direction),<br>new ADXP(&quot;Hamilton&quot;, AddressPartType.City),<br>new ADXP(&quot;Ontario&quot;, AddressPartType.State),<br>new ADXP(&quot;Canada&quot;, AddressPartType.Country)<br>);</pre>
<p><br>
The benefit of this shortcut is illustrated better with more complex sets such as SXPR and QSET. The following snippet represents the construction of an SXPR that represents numbers {1..10}, intersected with the result of a union of numbers {3..5} and {7..9}.</p>
<pre><br>SXPR&lt;INT&gt; result = new SXPR&lt;INT&gt;() <br>{<br>new IVL&lt;INT&gt;(1, 10),<br>new SXPR&lt;INT&gt;() {<br>Operator = SetOperator.Intersect,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Terms = new LIST&lt;SXCM&lt;INT&gt;&gt;() {<br>new IVL&lt;INT&gt;(3, 5),<br>new IVL&lt;INT&gt;(7, 9) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Operator = SetOperator.Inclusive <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }<br>&nbsp;&nbsp; } <br>};</pre>
<p><br>
Using the new constructor which is a shortcut, the following expression can be used:</p>
<pre><br>SXPR&lt;INT&gt; result = new SXPR&lt;INT&gt;(<br>new IVL&lt;INT&gt;(1, 10),<br>new SXPR&lt;INT&gt;(<br>new IVL&lt;INT&gt;(3, 5),<br>new IVL&lt;INT&gt;(7, 9) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Operator = SetOperator.Inclusive <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; }<br>) <br>&nbsp;&nbsp; { <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Operator = SetOperator.Intersect <br>&nbsp;&nbsp; }<br>);</pre>
<p><br>
These new shortcut methods are intended to assist developers even more than previous attempts at the Data Types implementation and are one of the many improvements in the Everest 1.0 data types library.</p>
</div><div class="ClearBoth"></div>

# SNOMED Expressions

<div class="wikidoc">
<p>First, I will provide a little bit of an overview. In HL7v3 clinical concepts within messages are represented using one of four different data types (two in the Data Types R2 specification). These are:</p>
<table border="0" width="597" cellspacing="0" cellpadding="2">
<tbody>
<tr>
<td valign="top" width="41"><strong>R1</strong></td>
<td valign="top" width="36"><strong>R2</strong></td>
<td valign="top" width="518"><strong>Summary</strong></td>
</tr>
<tr>
<td valign="top" width="41">CS</td>
<td valign="top" width="36">CS</td>
<td valign="top" width="518">Coded Simple - A simple code where only the code mnemonic is unknown</td>
</tr>
<tr>
<td valign="top" width="41">CV</td>
<td valign="top" width="36">CD.CV</td>
<td valign="top" width="518">Coded Value &ndash; A more complex code structure whereby the code system from which the mnemonic is taken is unknown at design time.</td>
</tr>
<tr>
<td valign="top" width="41">CE</td>
<td valign="top" width="36">CD.CE</td>
<td valign="top" width="518">Code with Equivalents &ndash; A CV instance where translations (or equivalents) can optionally be specified.</td>
</tr>
<tr>
<td valign="top" width="41">CD</td>
<td valign="top" width="36">CD</td>
<td valign="top" width="518">Concept Descriptor &ndash; A code mnemonic taken from a code system, optionally with one or more concept roles which qualify the primary code. For example, the code LEFT qualifies FOOT to mean LEFT FOOT.</td>
</tr>
<tr>
<td valign="top" width="41">CR</td>
<td valign="top" width="36">N/A</td>
<td valign="top" width="518">Concept Role - A name/value pair where the value concept qualifies the semantic meaning of the primary mnemonic by way of the named concept.</td>
</tr>
</tbody>
</table>
<p>Wait a minute! Notice some differences? Well, for starters CV and CE are no longer &quot;proper&quot; types according to the data types, they are flavors of CD. This is an appropriate change as they remain structurally identical to the R1 structures.</p>
<p><br>
The big change comes in the concept descriptor. Notice how the CR data type is not present in data types R2. When I first saw this I thought nothing of it, however when looking at how each are represented on the wire the difference is very pronounced.</p>
<p><br>
I'm a code kind of guy, so I thought I would explain this using code. First off, Everest uses a hybrid of DT R1 and R2, so the codified data types in Everest resemble those found in R1 (and are mapped to appropriate R2 flavors on formatting). With that in mind,
 let's represent the following example: &quot;severe burn on the skin between the fourth and fifth toes on the left side&quot;, in Everest.</p>
<p><br>
First, we create the primary code of &quot;burn&quot;:</p>
<pre><br>var burnCode = new CD&lt;string&gt;(&quot;284196006&quot;, &quot;2.16.840.1.113883.6.96&quot;) {<br>&nbsp;&nbsp;&nbsp; DisplayName = &quot;Burn of Skin&quot;,<br>&nbsp;&nbsp;&nbsp; CodeSystemName = &quot;SNOMED-CT&quot;,<br>&nbsp;&nbsp;&nbsp; CodeSystemVersion = &quot;2009&quot;<br>};</pre>
<p>Next, the we want to qualify &quot;Burn of Skin&quot; with a severity of &quot;Severe&quot;. This is accomplished by creating a CR instance:</p>
<pre><br>// Severity<br>var severityQualifier = new CR&lt;string&gt;(<br>new CV&lt;String&gt;(&quot;246112005&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Severity&quot; },<br>&nbsp;&nbsp; new CD&lt;String&gt;(&quot;24484000&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Severe&quot; }<br>);</pre>
<p><br>
Next, our code has a finding site. The burn was located on the skin between the fourth and fifth toes, so once again it is another CR instance:</p>
<pre><br>// Finding Site <br>var findingSiteQualifier = new CR&lt;String&gt;(<br>&nbsp;&nbsp;&nbsp; new CV&lt;String&gt;(&quot;363698007&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Finding Site&quot; },<br>&nbsp;&nbsp;&nbsp; new CD&lt;String&gt;(&quot;113185004&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Skin Between fourth and fifth toes&quot; }<br>);</pre>
<p><br>
Next, we want to describe the fact that the the burn was found on the skin between the fourth and fifth toes &quot;on the left hand side&quot;. Obviously we want to create another qualifier for this:</p>
<pre><br>// Laterality<br>var lateralityQualifier = new CR&lt;String&gt;(<br>new CV&lt;String&gt;(&quot;272741003&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Laterality&quot; },<br>new CD&lt;string&gt;(&quot;7771000&quot;, &quot;2.16.840.1.113883.6.96&quot;) <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; { DisplayName = &quot;Left Side&quot; }<br>);</pre>
<p><br>
But how would we structure these concept roles to describe the situation? First we have to look at each term and ask the question, &quot;What does this qualify&quot;. So, for example, does &quot;Laterality of Left Side&quot; qualify the burn? Technically no, the laterality qualifies
 the finding site (i.e.: We found the burn on the toes on the left hand side). So we want to add the lateralityQualifier to the findingSiteQualifier's value:</p>
<pre><br>// Laterality applies to the finding site<br>findingSiteQualifier.Value.Qualifier = new LIST&lt;CR&lt;string&gt;&gt;() { lateralityQualifier };</pre>
<p><br>
What does finding site qualify? Technically finding doesn't qualify the severity it qualifies the primary code (i.e.: The burn was &quot;found on&quot; the skin&hellip;), and the same with applies to the severity (i.e.: We didn't find a severe skin between toes, we found
 a severe burn). So we add these two qualifiers to the primary code:</p>
<pre><br>// Finding site and severity apply to primary code<br>burnCode.Qualifier = new LIST&lt;CR&lt;string&gt;&gt;() {<br>severityQualifier,<br>findingSiteQualifier<br>};</pre>
<p><br>
Now comes the easy part, when we format the data type using data types R1 formatter:</p>
<pre><br>var formatter = new MARC.Everest.Formatters.XML.ITS1.Formatter();<br>formatter.ValidateConformance = false;<br>formatter.GraphAides.Add(<br>&nbsp;&nbsp;&nbsp; typeof(MARC.Everest.Formatters.XML.Datatypes.R1.DatatypeFormatter)<br>);<br>// Setup the writer<br>StreamWriter sw = new StreamWriter(&quot;C:\\temp\\temp.xml&quot;);<br>XmlWriter xw = XmlWriter.Create(sw, new XmlWriterSettings() { Indent = true });<br>XmlStateWriter xsw = new XmlStateWriter(xw);<br>// Format and produce the XML file<br>try<br>{<br>&nbsp;&nbsp;&nbsp; xsw.WriteStartElement(&quot;code&quot;, &quot;urn:hl7-org:v3&quot;);&nbsp;&nbsp; xsw.WriteAttributeString(&quot;xmlns&quot;, &quot;xsi&quot;, null, <a href="http://www.w3.org/2001/XMLSchema-instance">http://www.w3.org/2001/XMLSchema-instance</a>);<br>&nbsp;&nbsp;&nbsp; var p = formatter.Graph(xsw, burnCode);&nbsp;&nbsp; sw.WriteEndElement();<br>}<br>finally<br>{&nbsp;&nbsp;&nbsp; xw.Close();&nbsp;&nbsp;&nbsp; sw.Flush();<br>&nbsp;&nbsp;&nbsp; formatter.Dispose();<br>}</pre>
<p><br>
The output of this is the following XML:</p>
<pre>&lt;code code=&quot;284196006&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; codeSystemName=&quot;SNOMED-CT&quot; codeSystemVersion=&quot;2009&quot; displayName=&quot;Burn of Skin&quot;&gt;<br>&lt;qualifier inverted=&quot;false&quot;&gt;<br>&lt;name code=&quot;246112005&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot;<br>displayName=&quot;Severity&quot; /&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;value code=&quot;24484000&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>displayName=&quot;Severe&quot; /&gt;<br>&nbsp;&nbsp; &lt;/qualifier&gt;<br>&nbsp;&nbsp; &lt;qualifier inverted=&quot;false&quot;&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;name code=&quot;363698007&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>displayName=&quot;Finding Site&quot; /&gt;<br>&lt;value code=&quot;113185004&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>displayName=&quot;Skin Between fourth and fifth toes&quot;&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;qualifier inverted=&quot;false&quot;&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;name code=&quot;272741003&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>displayName=&quot;Laterality&quot; /&gt;<br>&lt;value code=&quot;7771000&quot; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>displayName=&quot;Left Side&quot; /&gt;<br>&lt;/qualifier&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;/value&gt;<br>&lt;/qualifier&gt;<br>&lt;/code&gt;</pre>
<p><br>
But as I mentioned previously, CR is not supported in DT R2. So the question arises, &quot;How do I qualify a code in HL7v3 DT R2?&quot;. Well, the answer is not so simple. In DT R2, the concepts for SNOMED terms are described using an expression language defined by
 IHTSDO. The SNOMED expression for our scenario is:</p>
<pre><br>284196006|Burn of Skin|:{246112005|Severity|=24484000|Severe|,363698007|Finding Site|=(113185004|Skin Between fourth and fifth toes|:272741003|Laterality|=7771000|Left|)}</pre>
<p><br>
Intuitive right? Not really. So how do I represent this in a CD instance? Well, the answer is really ugly, and in my opinion violates first normal form (I will post an opinion post later about my thoughts of using 1NF in XML and how I think standards bodies
 seem to have forgotten it). <br>
Anyways, so what is this supposed to look like in DT R2? The answer is below:</p>
<pre><br>&lt;code code=&quot;284196006:{246112005=24484000,363698007=(113185004:272741003=7771000)}&quot; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; codeSystem=&quot;2.16.840.1.113883.6.96&quot; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; codeSystemName=&quot;SNOMED-CT&quot; <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; codeSystemVersion=&quot;2009&quot;&gt;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt;displayName value=&quot;Burn of Skin&quot;/&gt;<br>&lt;/code&gt;</pre>
<p><br>
I warned you it wasn't pretty. So how do you get Everest to format a concept descriptor like this? Well, the answer is simple, change this line of code:</p>
<pre><br>// Old Line: formatter.GraphAides.Add(typeof(MARC.Everest.Formatters.XML.Datatypes.R1.DatatypeFormatter));<br>formatter.GraphAides.Add(typeof(MARC.Everest.Formatters.XML.Datatypes.R2.DatatypeR2Formatter));</pre>
<p><br>
And Everest will automatically handle the generation of these expressions for SNOMED concepts. Parsing? It is the same. Everest 1.0's data types R2 formatter has been developed so that you are shielded from having to understand the complexities of SNOMED expressions.</p>
<p><br>
As a matter of fact, when parsing a SNOMED concept with an SNOMED expression, Everest will construct the appropriate hierarchy of concept roles for you.<br>
What do I think of this change in HL7? I think it was pointless, and simply over-complicates processing of XML instances. In my opinion, there is no need to mix the hierarchical language of SNOMED expressions as an attribute within the hierarcal container of
 XML. At least a framework like Everest has enough logic in the formatting of codes to shield the developer from changes like this.<br>
Next time, I'm going to blog about a good change in R2, changes in the continuous set expression data types (SXPR).</p>
</div><div class="ClearBoth"></div>

# Custom SOAP Headers

<div class="wikidoc">
<p>For this example, I'll show you how to read/write the WS-Addressing headers in a received message from the WCF connectors. First, to access the SOAP headers from a message received from a WcfServerConnector, you can simply access the Headers property on
 the WcfReceiveResult. The following code is written in the MessageAvailable event handler for a WcfServerConnector:</p>
<pre><br>static void conn_MessageAvailable(object sender, MARC.Everest.Connectors.UnsolicitedDataEventArgs e)<br>{<br>// Get the sending connector that raised the event<br>var connector = sender as WcfServerConnector;<br>if (connector == null)<br>throw new ArgumentException(&quot;Must be called from a WcfServerConnector&quot;, &quot;sender&quot;);<br>// Receive the message<br>var receiveResult = connector.Receive() as WcfReceiveResult;<br>Pretty standard Everest stuff, next we'll emit the value of the WS-Addressing headers:<br>if (receiveResult.Headers != null){<br>&nbsp;&nbsp;&nbsp;&nbsp; Console.WriteLine(receiveResult.Headers.To);<br>Console.WriteLine(receiveResult.Headers.Action);<br>}</pre>
<p><br>
We can access the Headers array just like any other WCF Header, this applies to constructing the response as well. To construct the response, populate the ResponseHeaders on the receiveResult prior to call &quot;Send()&quot; on the server connector.</p>
<pre><br>receiveResult.ResponseHeaders = new System.ServiceModel.Channels.MessageHeaders<br>(receiveResult.Headers.MessageVersion);<br>receiveResult.ResponseHeaders.Add(MessageHeader.CreateHeader(&quot;myHeader&quot;, &quot;urn:my-ns:com&quot;, &quot;Value&quot;));<br>connector.Send(new MCCI_IN000002CA(), receiveResult);</pre>
<p><br>
This code will return the following soap header:</p>
<pre><br>&lt; tns:myHeader xmlns:tns=&quot;urn:my-ns:com&quot;&gt;Value&lt;/tns:myHeader&gt;</pre>
<p><br>
More complex headers can be added the same way you would add standard System.ServiceModel.Channel.MessageHeader objects. It is also possible to send message headers using the overridden Send() method on the WcfClientConnector:</p>
<pre><br>var conn = new WcfClientConnector();<br>// trimmed<br>MessageHeaders messageHeaders = new System.ServiceModel.Channels.MessageHeaders(MessageVersion.Soap12);messageHeaders.Add(MessageHeader.CreateHeader(&quot;myHeader&quot;, &quot;urn:my-ns:com&quot;, &quot;Value&quot;));<br>conn.Send(instance, messageHeaders);</pre>
</div><div class="ClearBoth"></div>

# Emitting XML Comments

<div class="wikidoc">
<p>A great question came to me the other day. How does one pretty-up the XML output generated by Everest so that humans can read the XML? Of course there are the old tricks of indentation and formatting the output however that only gets us so far.</p>
<p><br>
Wouldn&rsquo;t it be great if Everest had the capacity to emit comments in the XML instances. Sadly this isn&rsquo;t a use case for vanilla Everest however there is a way to easily do this in the upcoming 1.2 release of Everest (being released on June 5th BTW).</p>
<p><br>
Lets say I want to emit a comment that annotates the &lt;acceptAckCode&gt; element, something like this:</p>
<pre><br>&lt;?xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;<br>&lt;PRPA_IN101301UV02 ITSVersion=&quot;XML_1.0&quot; xmlns:xsi=&quot;<a href="http://www.w3.org/2001/XMLSchema-instance">http://www.w3.org/2001/XMLSchema-instance</a>&quot; xmlns=&quot;urn:hl7-org:v3&quot;&gt;<br>&nbsp; &lt;id root=&quot;F043D3BF-02C4-48BF-8C7E-6FFEE7D75B52&quot; /&gt;<br>&nbsp; &lt;creationTime value=&quot;20130531171954.939-0400&quot; /&gt;<br>&nbsp; &lt;interactionId root=&quot;2.16.840.1.113883.1.18&quot; extension=&quot;PRPA_IN101301UV02&quot; /&gt;<br>&nbsp; &lt;processingCode code=&quot;T&quot; /&gt;<br>&nbsp; &lt;processingModeCode code=&quot;A&quot; /&gt;<br>&nbsp; &lt;!--The acknowledgement code&mdash;&gt;<br>&nbsp; &lt;acceptAckCode code=&quot;AL&quot; /&gt;<br>&lt;/PRPA_IN101301UV02&gt;</pre>
<p>The way I would do this is to add an extension method to IGraphable to allow users to add commnents:</p>
<pre>public static class CommentExtension<br>{<br>&nbsp;&nbsp;&nbsp; private static List&lt;KeyValuePair&lt;IGraphable, String&gt;&gt; s_comments = new List&lt;KeyValuePair&lt;IGraphable, string&gt;&gt;();<br>&nbsp;&nbsp;&nbsp; private static Object s_syncLock = new object();<br>&nbsp;&nbsp;&nbsp; public static void AddComment(this IGraphable me, string comment)<br>&nbsp;&nbsp;&nbsp; {<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; lock (s_syncLock)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; s_comments.Add(new KeyValuePair&lt;IGraphable, String&gt;(me, comment));<br>&nbsp;&nbsp;&nbsp; }<br>&nbsp;&nbsp;&nbsp; public static string GetComment(this IGraphable me)<br>&nbsp;&nbsp;&nbsp; {<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; return s_comments.Find(o=&gt;o.Key == me).Value;<br>&nbsp;&nbsp;&nbsp; }<br>}</pre>
<p><br>
We can then extend the XmlIts1Formatter and override the WriteElementUtil method to emit the comment added prior to serializing the element:</p>
<pre>public class XmlIts1FormatterWithComments : XmlIts1Formatter<br>{<br>&nbsp;&nbsp;&nbsp; public override void WriteElementUtil(System.Xml.XmlWriter s, <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; string elementName, <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MARC.Everest.Interfaces.IGraphable g, <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Type propType, <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; MARC.Everest.Interfaces.IGraphable context, <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; XmlIts1FormatterGraphResult resultContext)<br>&nbsp;&nbsp;&nbsp; {<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; String comment = g.GetComment();<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; if (comment != null)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; s.WriteComment(comment);<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; base.WriteElementUtil(s, elementName, g, propType, context, resultContext);<br>&nbsp;&nbsp;&nbsp; }<br>}</pre>
<p><br>
Then, using this new formatter we can simply add the comment and format!</p>
<pre><br>PRPA_IN101301UV02 test = new PRPA_IN101301UV02(<br>&nbsp;&nbsp;&nbsp; Guid.NewGuid(),<br>&nbsp;&nbsp;&nbsp; DateTime.Now,<br>&nbsp;&nbsp;&nbsp; PRPA_IN101301UV02.GetInteractionId(),<br>&nbsp;&nbsp;&nbsp; ProcessingID.Training,<br>&nbsp;&nbsp;&nbsp; ProcessingMode.Archive,<br>&nbsp;&nbsp;&nbsp; AcknowledgementCondition.Always);<br>test.AcceptAckCode.AddComment(&quot;The acknowledgement code&quot;);<br>var formatter = new XmlIts1FormatterWithComments();<br>formatter.GraphAides.Add(new DatatypeFormatter());<br>formatter.Graph(Console.OpenStandardOutput(), test);<br>Console.ReadKey();</pre>
<p><br>
Hope that helps anyone else with the same problem!</p>
</div><div class="ClearBoth"></div>
