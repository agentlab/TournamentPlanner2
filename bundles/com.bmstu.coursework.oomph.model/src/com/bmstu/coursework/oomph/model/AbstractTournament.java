/**
 *
 */
package com.bmstu.coursework.oomph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vilkova
 *
 */
public abstract class AbstractTournament {
	protected List<Player> players;
	protected Collection<Match> matchs;

	public AbstractTournament(List<Player> players) {
		this.players = players;
		matchs = new ArrayList();
	}

	public abstract Collection<Match> generateMatchs(); //throws *exception*

	public abstract List<Player> getRating();
}

