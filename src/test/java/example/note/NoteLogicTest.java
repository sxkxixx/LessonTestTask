package example.note;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

public class NoteLogicTest {
    public NoteLogic noteLogic;
    private static final String noteName = "Название заметки";

    @Before
    public void addNote() {
        this.noteLogic = new NoteLogic();
        this.noteLogic.handleMessage("/add " + noteName);
    }

    /**
     * Тестирует команду /add, допольнительно проверяет команду /notes;
     */
    @Test
    public void testAddCommand() {
        String response = this.noteLogic.handleMessage("/notes");
        Assert.assertEquals(
                "Your notes:\n1. " + noteName,
                response);
    }

    /**
     * Тестирует команда /edit для изменения заметки;
     */
    @Test
    public void testEditCommand() {
        String changedName = "алфавит";
        this.noteLogic.handleMessage("/edit " + changedName);
        String response = this.noteLogic.handleMessage("/notes");
        Assert.assertEquals(
                "Your notes:\n1. " + changedName,
                response);
    }

    /**
     * Тестирует команду /del для удаления заметки.
     */
    @Test
    public void testDeleteCommand() {
        this.noteLogic.handleMessage("/add FirstNote");
        this.noteLogic.handleMessage("/add SecondNote");
        this.noteLogic.handleMessage("/del 2");

        Assert.assertEquals("Your notes:\n1. FirstNote", this.noteLogic.handleMessage("/notes"));
    }
}
