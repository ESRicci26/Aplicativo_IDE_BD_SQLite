package util;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;

public class PermiteNumerosLimiteDigitadoJTF extends PlainDocument {
    private static final long serialVersionUID = 1L;

    private int maxLength;

    public PermiteNumerosLimiteDigitadoJTF(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str != null && getLength() + str.length() <= maxLength) {
            // Habilita apenas números
            super.insertString(offset, str.replaceAll("[^0-9]", ""), attr);
        }
    }

    @Override
    public void replace(int offset, int length, String str, AttributeSet attr) throws BadLocationException {
        if (str != null && getLength() + str.length() - length <= maxLength) {
            // Habilita apenas números
            super.replace(offset, length, str.replaceAll("[^0-9]", ""), attr);
        }
    }
}
