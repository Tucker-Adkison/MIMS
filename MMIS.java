import javax.swing.JFrame;

class MMIS {

    private static int frame_size = 400;
    private static JFrame frame;
    public static void main(String[] args) {
        makeFrame();
    }

    private static void makeFrame() {
        frame = new JFrame("Medical Information System");
        frame.setSize(frame_size, frame_size);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Layout layout = new Layout();
        layout.addTabPane();
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrameSize(int size) {
        frame.setSize(size, size);
        frame.revalidate();
        frame_size = size;

    }
}