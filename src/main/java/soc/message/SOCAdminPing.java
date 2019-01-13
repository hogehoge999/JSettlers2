/**
 * Java Settlers - An online multiplayer version of the game Settlers of Catan
 * Copyright (C) 2003  Robert S. Thomas <thomas@infolab.northwestern.edu>
 * Portions of this file Copyright (C) 2010,2014,2017-2018 Jeremy D Monin <jeremy@nand.net>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * The maintainer of this program can be reached at jsettlers@nand.net
 **/
package soc.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * This message is a way for the admin to test
 * if a robot is connected and running
 * in a game where the admin user is a member.
 *
 * @author Robert S Thomas
 * @see SOCServerPing
 * @see SOCTimingPing
 */
public class SOCAdminPing extends SOCMessage
    implements SOCMessageForGame
{
    private static final long serialVersionUID = 1111L;  // last structural change v1.1.11

    /**
     * Name of the game.
     */
    private String game;

    /**
     * Create a AdminPing message.
     *
     * @param ga  name of game
     */
    @JsonCreator
    public SOCAdminPing(@JsonProperty("game")String ga)
    {
        messageType = ADMINPING;
        game = ga;
    }

    /**
     * @return the name of the game having the robot and the admin user as members
     */
    public String getGame()
    {
        return game;
    }

    /**
     * ADMINPING sep game
     *
     * @return the command String
     */
    public String toCmd()
    {
        return toCmd(game);
    }

    /**
     * ADMINPING sep game
     *
     * @param ga  the new game name
     * @return    the command string
     */
    public static String toCmd(String ga)
    {
        try
        {
        	SOCAdminPing soc = new SOCAdminPing(ga);
	        return mapper.writeValueAsString(soc);
        }
        catch (Exception e)
        {
        }

        return ADMINPING + sep + ga;
    }

    /**
     * Parse the command String into a AdminPing message
     *
     * @param s   the String to parse
     * @return    an AdminPing message
     */
    public static SOCAdminPing parseDataStr(String s)
    {
        try
        {
        	SOCAdminPing soc = mapper.readValue(s, SOCAdminPing.class);
        	return soc;
        }
        catch (Exception e)
        {
        }
        return new SOCAdminPing(s);
    }

    /**
     * @return a human readable form of the message
     */
    public String toString()
    {
        return "SOCAdminPing:game=" + game;
    }

}
