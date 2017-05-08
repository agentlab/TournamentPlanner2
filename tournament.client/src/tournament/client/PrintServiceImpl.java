/**
 *
 */
package tournament.client;

/**
 * @author Vilkova
 *
 */
public class PrintServiceImpl implements PrintService {
	@Override
	public void print(String message) {
		System.err.println(message);
	}
}
