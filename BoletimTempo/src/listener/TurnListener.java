package listener;

import model.InterfaceBoletimGenerator;
import java.util.EventListener;

public interface TurnListener extends EventListener
{
	public void TurnFinished(InterfaceBoletimGenerator obj);
}
