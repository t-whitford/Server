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
	

	/**
	 * Calls generatePage with a default title
	 * @param body The body to instert in place of &lt!--body--&gt in autoPage.html
	 * @return A string object containing the full page html
	 */
	public static String generatePage(String body){
		return generatePage("Generated page", body);
		
	}
	
	/**
	 * 
	 * @param body The body to insert in place of &lt!--body--&gt in autoPage.html
	 * @param title The title to insert in plac of &lt!--title--&gt in autoPage.html
	 * @return A string object containing the full page html
	 */
	public static String generatePage(String title, String body){
		return "";
	}

}
