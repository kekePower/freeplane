package org.freeplane.features.map.mindmapmode;

import java.awt.event.ActionEvent;

import org.freeplane.core.ui.AFreeplaneAction;
import org.freeplane.features.map.AlwaysUnfoldedNode;
import org.freeplane.features.map.NodeModel;
import org.freeplane.features.mode.Controller;

@SuppressWarnings("serial")
public class SetAlwaysUnfoldedNodeFlagsAction extends AFreeplaneAction {

	private AlwaysUnfoldedNode flagController;

	public SetAlwaysUnfoldedNodeFlagsAction() {
		super("SetAlwaysUnfoldedNodeFlagsAction");
		flagController = Controller.getCurrentModeController().getExtension(AlwaysUnfoldedNode.class);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final NodeModel rootNode = Controller.getCurrentController().getMap().getRootNode();
		final NodeModel node = rootNode;
		setAlwaysUnfoldedNodeFlags(node);
	}

	private void setAlwaysUnfoldedNodeFlags(final NodeModel node) {
		if(! node.isFolded() && ! node.isRoot() && node.isVisible()){
			flagController.undoableActivateHook(node, flagController);
		}
		for(NodeModel child : node.getChildren())
			setAlwaysUnfoldedNodeFlags(child);
	}

}
