/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.control;

import back.Game;
import front.Window;
import java.awt.Point;
import network.NetworkListener;
import network.message.NetworkMessage;
import network.message.TurnStartMessage;
import network.peer.MasterPeer;

/**
 *
 * @author Szlatyka
 */
public class NetworkHostGameField extends GameField implements NetworkListener
{
	private MasterPeer peer = null;
	
	public NetworkHostGameField(Window window, Game game)
	{
		super(window, game);
		
		this.peer = new MasterPeer(10517);
		this.peer.start();
	}
	
	@Override public void movePlayer(Point p)
	{
		super.movePlayer(p);
		this.peer.send(new TurnStartMessage(NetworkMessage.MOVE, p));
	}
	
	@Override public void buildWall(Point p)
	{
		super.buildWall(p);
		this.peer.send(new TurnStartMessage(NetworkMessage.BUILDWALL, p));
	}
	
	@Override public void cutHedge(Point p)
	{
		super.cutHedge(p);
		this.peer.send(new TurnStartMessage(NetworkMessage.REMOVEHEDGE, p));
	}

	@Override
	public void MessageReceived(NetworkMessage msg)
	{
		switch(msg.getType())
		{
			case NetworkMessage.TURNSTART:
				break;
			case NetworkMessage.TIMEOUT:
				break;
			case NetworkMessage.GAMEOVER:
				break;
		}
	}
	
}
