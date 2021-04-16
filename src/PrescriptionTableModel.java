import java.util.List;
import javax.swing.table.AbstractTableModel;
 
 
public class PrescriptionTableModel<T> extends AbstractTableModel
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final List<Prescription> prescription_list;
     
    private final String[] columnNames = new String[] {
            "First Name", "Second Name", "Drug", "Quantity", "Time-Stamp"
    };
    private final Class<?>[] columnClass = new Class[] {
        String.class, String.class, String.class, String.class, String.class
    };
 
    public PrescriptionTableModel(List<Prescription> prescription_list)
    {
        this.prescription_list = prescription_list;
    }
     
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
 
    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (prescription_list.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
 
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
 
    @Override
    public int getRowCount()
    {
        return prescription_list.size();
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Prescription row = prescription_list.get(rowIndex);
        if(0 == columnIndex) {
            return row.getName()[0];
        }
        else if(1 == columnIndex) {
            return row.getName()[1];
        }
        else if(2 == columnIndex) {
            return row.getDrug();
        }
        else if(3 == columnIndex) {
            return row.getQuantity();
        }
        else if(4 == columnIndex) {
            return row.getTimestamp();
        }
        return null;
    }
}