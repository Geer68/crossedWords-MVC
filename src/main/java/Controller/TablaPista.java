package Controller;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class TablaPista {

    private final String[][] datosVertical;
    private final String[][] datosHorizontal;

    public TablaPista() {
        this.datosHorizontal = new String[][]{{"2", "Ranura de expansión en una placa base"}, {"4", "Suministro de energía para una PC"}, {"5", "Extensión de archivos Python"},{"6", "Ruta de comunicación en una computadora"}, {"10", "Componentes físicos de una computadora"}, {"12", "Dispositivos de entrada en una interfaz gráfica"}, {"14", "Tipo de memoria temporal"}, {"17", "Superficie de control para el mouse"}, {"18", "Dirección web"}, {"21", "Unidad de lectura de discos ópticos"}, {"24", "Computadora central que brinda servicios a otras"}, {"26", "Dispositivo de entrada principal"}, {"29", "Unidad básica de información binaria"}, {"31", "Red global de computadoras"}, {"32", "Dispositivo para imprimir documentos"}, {"34", "Dispositivo de almacenamiento"}, {"36", "Palabra en inglés"}, {"37", "Dispositivo para conectar a internet"}, {"38", "Sistema operativo de Apple"}, {"39", "Empresa de software"}};
        this.datosVertical = new String[][]{{"1", "JavaScript"}, {"3", "Periférico de entrada"}, {"7", "Formato de documento portable"}, {"8", "Unidad básica de información digital"}, {"9", "Ideal con el 1 a muchos en base de datos"}, {"11", "Almacén de archivos"}, {"13", "Para insertar espacio entre caracteres"}, {"15", "Dispositivo para visualizar imágenes"}, {"16", "Programa o conjunto de instrucciones"}, {"19", "Conexión de computadoras"}, {"20", "Programa para gestionar base de datos en Office"}, {"22", "Información procesada por una computadora"}, {"23", "Dispositivo de lectura de discos ópticos"}, {"25", "Almacenamiento de información digital"}, {"27", "Sistema operativo de Microsoft"}, {"28", "Representación gráfica de un programa o archivo"}, {"30", "Sistema de gestión de base de datos"}, {"33", "Almacén de información digital"}, {"35", "Usuario principal"}};
    }

    public void pistasHorizontales(DefaultTableModel tabla) {
        for (String[] pista : datosHorizontal) {
            tabla.addRow(pista);
        }
    }

    public void pistasVerticales(DefaultTableModel tabla) {
        for (String[] pista : datosVertical) {
            tabla.addRow(pista);
        }
    }

    public void prettyTable(JTable tabla) {
        TableColumnModel columnModelHorizontal = tabla.getColumnModel();

        columnModelHorizontal.getColumn(0).setPreferredWidth((int) (tabla.getWidth() * 0.1));
        columnModelHorizontal.getColumn(1).setPreferredWidth((int) (tabla.getWidth() * 0.9));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        columnModelHorizontal.getColumn(0).setCellRenderer(centerRenderer);
    }
}
