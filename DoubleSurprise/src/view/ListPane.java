package view;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class ListPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3183368667284297069L;
	private JList jl = null;

	public ListPane() {
		jl = new JList();
		jl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(jl), BorderLayout.CENTER);

	}

	public void setContents(Object[] items) {
		jl.setListData(items);
	}

	public Object getSelectedItem() {
		if(jl.getSelectedValues().length > 0) return jl.getSelectedValues()[0];
		return null;
	}

	public void addClickListener(ListSelectionListener listener) {
		jl.addListSelectionListener(listener);
	}
	public void setSelectedIndex(int index) {
		jl.setSelectedIndex(index);
	}
}
