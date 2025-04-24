package util;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Teclado extends PlainDocument{
	private static final long serialVersionUID = 1L;

@Override
public void insertString(int offset, String str, javax.swing.text.AttributeSet attr)
throws BadLocationException{
//super.insertString(offset, str.replaceAll("[^a-z|^A-Z|^ ]",""), attr);  //Habilita só letras Minúsculas, Maiúsculas e Espaço
//super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z|^]",""), attr);  //Habilita só letras Maiúsculas e Espaço mesmo com o INSERT ATIVADO
super.insertString(offset, str.replaceAll("[^0-9|^.|^,|^-]",""), attr);  //Habilita NÚMEROS, PONTO, VÍRGULA E TRAÇO

}

public void replace(int offset, String str, javax.swing.text.AttributeSet attr)
throws BadLocationException{
//super.insertString(offset, str.replaceAll("[^a-z|^A-Z|^ ]",""), attr);  //Habilita só letras Minúsculas, Maiúsculas e Espaço
//super.insertString(offset, str.toUpperCase().replaceAll("[^a-z|^A-Z|^ ]",""), attr);  //Habilita só letras Maiúsculas e Espaço mesmo com o INSERT ATIVADO
super.insertString(offset, str.replaceAll("[^0-9|^.|^,|^-]",""), attr);  //Habilita NÚMEROS, PONTO, VÍRGULA E TRAÇO

}

}

