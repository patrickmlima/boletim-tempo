package listener;

import model.InterfaceBoletimGenerator;
import java.util.EventListener;
/**
 * listener.TurnListener
 * Creation date: 08/11/2014
 * @author Patrick M Lima
 * Interface dos listeners de fim de um turno.
 */
public interface TurnListener extends EventListener
{
	public void TurnFinished(InterfaceBoletimGenerator obj);
}
