package unisinos.maps;
/**
 *  N�s (Giordano Trombetta, Josu� Silva, Fabio Junqueira), garantimos que: 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


    public class InputDialog {

        public static Integer readInt(String s){
            int num = 0;
            String temp =" ";
            while (temp != null){
                try {
                    temp = JOptionPane.showInputDialog(s);
                    if (temp == null)
                        return null;
                    num = Integer.valueOf(temp);
                    return num;
                }
                catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Valor incorreto", "ERRO", JOptionPane.ERROR_MESSAGE, null);
                }
            }
            return num ;
        }

        public static Double readDouble(String s){
            double num = 0;
            String temp =" ";
            while (temp != null){
                try {
                    temp = JOptionPane.showInputDialog(s);
                    if (temp == null)
                        return null;
                    num = Double.valueOf(temp);
                    return num;
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Valor incorreto", "ERRO", JOptionPane.ERROR_MESSAGE, null);
                } catch (Exception e){
                    System.out.println("Erro: erro desconhecido: "+e.getMessage());
                }
            }
            return num ;
        }

        public static Date readDate(String s){
            Date dt = null;
            String temp = " ";
            while (temp != null){
                try{
                    temp = JOptionPane.showInputDialog(s);
                    if (temp == null)
                        return null;
                    SimpleDateFormat std = new SimpleDateFormat("dd/MM/yyyy");
                    dt = std.parse(temp);
                    return dt;
                }catch (ParseException e){
                    JOptionPane.showMessageDialog(null, "Data Incorreta", "ERRO", JOptionPane.ERROR_MESSAGE, null);
                }catch (NullPointerException e){

                }

            }
            return dt;
        }
    }

