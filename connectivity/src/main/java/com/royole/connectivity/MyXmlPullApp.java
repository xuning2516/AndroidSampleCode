package com.royole.connectivity;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * An example of an application that uses XMLPULL V1 API.
 *
 * @author <a href="http://www.extreme.indiana.edu/~aslom/">Aleksander Slominski</a>
 */
public class MyXmlPullApp
{

    public final static String SAMPLE_XML =
            "<?xml version=\"1.0\"?>\n"+
                    "\n"+
                    "<poem xmlns=\"http://www.megginson.com/ns/exp/poetry\">\n"+
                    "<title>Roses are Red</title>\n"+
                    "<l>Roses are red,</l>\n"+
                    "<l>Violets are blue;</l>\n"+
                    "<l>Sugar is sweet,</l>\n"+
                    "<l>And I love you.</l>\n"+
                    "</poem>";

    public void main (String args[])
            throws XmlPullParserException, IOException
    {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        System.out.println("parser implementation class is "+xpp.getClass());

        MyXmlPullApp app = new MyXmlPullApp();

        if(args.length == 0) {
            System.out.println("Parsing simple sample XML");//:\n"+ SAMPLE_XML);
            xpp.setInput( new StringReader( SAMPLE_XML ) );
            app.processDocument(xpp);
        } else {
            for (int i = 0; i < args.length; i++) {
                System.out.println("Parsing file: "+args[i]);
                xpp.setInput ( new FileReader ( args [i] ) );
                app.processDocument(xpp);
            }
        }
    }


    public void processDocument(XmlPullParser xpp)
            throws XmlPullParserException, IOException
    {
        int eventType = xpp.getEventType();
        do {
            if(eventType == xpp.START_DOCUMENT) {
                System.out.println("Start document");
            } else if(eventType == xpp.END_DOCUMENT) {
                System.out.println("End document");
            } else if(eventType == xpp.START_TAG) {
                processStartElement(xpp);
            } else if(eventType == xpp.END_TAG) {
                processEndElement(xpp);
            } else if(eventType == xpp.TEXT) {
                processText(xpp);
            }
            eventType = xpp.next();
        } while (eventType != xpp.END_DOCUMENT);
    }


    public void processStartElement (XmlPullParser xpp)
    {
        String name = xpp.getName();
        String uri = xpp.getNamespace();
        if ("".equals (uri)) {
            System.out.println("Start element: " + name);
        } else {
            System.out.println("Start element: {" + uri + "}" + name);
        }
    }


    public void processEndElement (XmlPullParser xpp)
    {
        String name = xpp.getName();
        String uri = xpp.getNamespace();
        if ("".equals (uri))
            System.out.println("End element: " + name);
        else
            System.out.println("End element:   {" + uri + "}" + name);
    }

    int holderForStartAndLength[] = new int[2];

    public void processText (XmlPullParser xpp) throws XmlPullParserException
    {
        char ch[] = xpp.getTextCharacters(holderForStartAndLength);
        int start = holderForStartAndLength[0];
        int length = holderForStartAndLength[1];
        System.out.print("Characters:    \"");
        for (int i = start; i < start + length; i++) {
            switch (ch[i]) {
                case '\\':
                    System.out.print("\\\\");
                    break;
                case '"':
                    System.out.print("\\\"");
                    break;
                case '\n':
                    System.out.print("\\n");
                    break;
                case '\r':
                    System.out.print("\\r");
                    break;
                case '\t':
                    System.out.print("\\t");
                    break;
                default:
                    System.out.print(ch[i]);
                    break;
            }
        }
        System.out.print("\"\n");
    }
}

