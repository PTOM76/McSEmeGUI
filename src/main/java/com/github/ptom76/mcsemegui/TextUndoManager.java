package com.github.ptom76.mcsemegui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.undo.UndoManager;

public class TextUndoManager extends UndoManager implements KeyListener {

    public TextUndoManager()
    {
        super();
    }

    public void keyPressed(KeyEvent e)
    {

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_Z:
                if (e.isControlDown() && this.canUndo())
                {
                    this.undo();
                    e.consume();
                }
                break;
            case KeyEvent.VK_Y:
                if (e.isControlDown() && this.canRedo())
                {
                    this.redo();
                    e.consume();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        // NOP
    }

    public void keyTyped(KeyEvent e)
    {
        // NOP
    }
}