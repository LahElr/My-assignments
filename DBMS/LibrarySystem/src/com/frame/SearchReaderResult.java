package com.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

public class SearchReaderResult extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public SearchReaderResult(Vector<Vector<String>> sheet) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 700, 500);
		setTitle("Result");
		contentPane = new JPanel();
		contentPane.setSize(700, 100);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700,1000));

		String[] colNamesA = { "reader id", "reader name" };
		Vector<String> colNames = new Vector<String>(2);
		for (int i = 0; i < 2; i++) {
			colNames.add(colNamesA[i]);
		}
		table = new JTable(sheet, colNames);
		table.setFillsViewportHeight(true);
		// table.setEnabled(false);

		TableColumn col = null;
		// 调整宽度（其实是初始比例）
		for (int i = 0; i < 2; i++) {
			col = table.getColumnModel().getColumn(i);
			switch (i) {
			case 0: {
				col.setPreferredWidth(150);
				break;
			}
			case 1: {
				col.setPreferredWidth(30);
				break;
			}
			}
		}
		
		table.setPreferredScrollableViewportSize(new Dimension(650, 402));//表格大小
		//滚动轴面板
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) {
			
			//private static final long serialVersionUID = 227279485298731171L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(650, 402);//控制滚动轴面板大小
			}
		};

		panel.add(scrollPane);
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
