package PageGeneration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.io.FileUtils;

import ServerMain.Server;


/**
 * Methods to automatically generate a page with a body and title.
 * <p>Requires an autoPage.html in the root folder of the webpage.
 * <p>autoPage.html should be a complete page.</p> 
 * <p>The class will replace:
 * <ul>
 * <li>&lt!--body--&gt : With the supplied "body" parameter </li> 
 * <li>&lt!--title--&gt : With the supplied title. (not needed) </li>
 * </ul> </p>
 * @author Tom
 *
 */
public class pageGenerator {
	
	
	final static String autoPage = "autoPage.html";
	final static String hedderReplace = "<!--title-->";
	final static String bodyReplace = "<!--body-->";

	/**
	 * Calls generatePage with a default title
	 * @param body The body to instert in place of &lt!--body--&gt in autoPage.html
	 * @return A string object containing the full page html
	 * @throws IOException 
	 */
	public static String generatePage(String body) throws IOException{
		return generatePage("Generated page", body);
		
	}
	
	/**
	 * 
	 * @param body The body to insert in place of &lt!--body--&gt in autoPage.html
	 * @param title The title to insert in plac of &lt!--title--&gt in autoPage.html
	 * @return A string object containing the full page html
	 * @throws IOException 
	 */
	public static String generatePage(String title, String body) throws IOException{
		
		String pageOutput = "\n"; 
		
		//Open file.
		
		
		File file = new File(Server.getRootDirectory(), autoPage);
		
		List<String> list = FileUtils.readLines(file);
		
		ListIterator<String> iterator = list.listIterator();
		
		
		for(int i = 0; i < list.size(); i++)
		{
			String line = iterator.next();
			
			if(line.contains(hedderReplace))
				pageOutput = pageOutput + "<title>" + title + "</title>\n";
			else if(line.contains(bodyReplace))
				pageOutput = pageOutput + body + "\n";
			else
				pageOutput = pageOutput + line + "\n";
			
		}
		
		return pageOutput;
	}

}
