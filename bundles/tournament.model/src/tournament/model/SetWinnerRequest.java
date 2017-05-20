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
	private int idMatch;

	/**
	 * @return the player's id
	 */
	public int getIdPlayer() {
		return idPlayer;
	}

	/**
	 * @return the match
	 */
	public int getIdMatch() {
		return idMatch;
	}

	/**
	 * @param idPlayer the idPlayer to set
	 */
	public void setIdPlayer(int idPlayer) {
		this.idPlayer = idPlayer;
	}

	/**
	 * @param idMatch the idMatch to set
	 */
	public void setIdMatch(int idMatch) {
		this.idMatch = idMatch;
	}
}
