package com.huoyan.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.huoyan.main.MyPlayer;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	EmbeddedMediaPlayerComponent playerComponent;
	private JPanel panel;
	private JButton btnPlay;
	private JButton btnPause;
	private JButton btnStop;
	private JPanel controlPanel;
	private JProgressBar progress;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpenVideo;
	private JMenuItem mntmOpenSubtitle;
	private JMenuItem mntmExit;
	private JSlider slider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmOpenVideo = new JMenuItem("Open Video");
		mnFile.add(mntmOpenVideo);
		
		mntmOpenSubtitle = new JMenuItem("Open Subtitle");
		mnFile.add(mntmOpenSubtitle);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mntmOpenVideo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPlayer.openVideo();	
			}
		});
		
		mntmOpenSubtitle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPlayer.openSubtitle();
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MyPlayer.exit();
				
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel videopanel = new JPanel();
		contentPane.add(videopanel);
		videopanel.setLayout(new BorderLayout(0, 0));
		
		playerComponent = new EmbeddedMediaPlayerComponent();
		
		videopanel.add(playerComponent);
		
		panel = new JPanel();
		videopanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		controlPanel = new JPanel();
		panel.add(controlPanel, BorderLayout.CENTER);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnStop = new JButton("Stop");
		controlPanel.add(btnStop);
		
		btnPlay = new JButton("Play");
		controlPanel.add(btnPlay);
		
		btnPause = new JButton("Pause");
		controlPanel.add(btnPause);
		
		slider = new JSlider();
		slider.setValue(100);
		slider.setMaximum(120);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MyPlayer.setVol(slider.getValue());
			}
		});
		
		controlPanel.add(slider);
		
		progress = new JProgressBar();
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int X = e.getX();
				MyPlayer.jumpTo((float)X/progress.getWidth());	
			}
		});
		progress.setStringPainted(true);
		panel.add(progress, BorderLayout.NORTH);
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyPlayer.pause();
			}
		});
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				MyPlayer.play();
			}
		});
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MyPlayer.stop();
			}
		});
	}
	
	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
	}
	
	public JProgressBar getJProgressBar() {
		return progress;
	}

}
