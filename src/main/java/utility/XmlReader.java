package utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReader {

	/*This function will read test data based upon the test data XML file and the test case name.*/
	public Object[][] testData(String filName, String tcName) throws MalformedURLException, IOException, ParserConfigurationException, SAXException 
	{		
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		Document doc = docBuilder.parse(new File(filName));

		doc.getDocumentElement().normalize();

		NodeList test_Case = doc.getElementsByTagName(tcName);
		
		NodeList child_Nodes_In_Test_Case=test_Case.item(0).getChildNodes();
		int number_Of_Child_Nodes_In_Test_Case=test_Case.item(0).getChildNodes().getLength();
		int number_Of_Data_Nodes_In_Test_Case=0;
		
		for(int i=0;i<number_Of_Child_Nodes_In_Test_Case;i++)
		{
			if(child_Nodes_In_Test_Case.item(i).getNodeType()==Node.ELEMENT_NODE)
			{
				number_Of_Data_Nodes_In_Test_Case++;
			}
		}
		
		int test_Case_Instances=test_Case.getLength();
		
		Object Data[][]=new Object[test_Case_Instances][number_Of_Data_Nodes_In_Test_Case];
		
		for(int i=0;i<test_Case_Instances;i++)
		{
			int k=0;
			Node current_Test_Case_Instance=test_Case.item(i);
			for(int j=0; j<number_Of_Child_Nodes_In_Test_Case; j++)
			{
				Node node=current_Test_Case_Instance.getChildNodes().item(j);
				
				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					Data[i][k]=node.getTextContent();
					k++;
				}
			}
			
		}
		return Data;
	}
}