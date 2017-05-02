/**
 *
 */
package tournament.model;

/**
 * @author Vilkova
 *
 */
public class SetWinnerRequest {
	private int idPlayer;
	private Match match;

	/**
	 * @return the player's id
	 */
	public int getIdPlayer() {
		return idPlayer;
	}

	/**
	 * @return the match
	 */
	public Match getMatch() {
		return match;
	}
}
