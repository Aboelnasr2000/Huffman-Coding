import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;



public class HuffmanCodeSolution {

    static long startTime;
    static long endTime;
    static long timeElapsed;

    private static Map<Character, String> charPrefixHashMap = new HashMap<>();
    static HuffmanNode root;

    public static void main(String[] args) throws IOException {

        System.out.println("Enter file name");
        Scanner scanner = new Scanner(System.in) ;
        String filename = scanner.next();
        System.out.println("1 - Read & Compress Text File  " );
        System.out.println("2 - Read & Compress  File  " );
        int choice = scanner.nextInt();

if(choice==1) {
    startTime = System.currentTimeMillis();
    String message = readMessage(filename);

    String s = encode(message);

    BitSet bitSet = toBinary(s);
    compressedwritetofile(bitSet);
    endTime = System.currentTimeMillis();
    timeElapsed = endTime - startTime;
    System.out.println("Time to Compress  = " + timeElapsed + " Milliseconds");
    System.out.println("Encoded:" + s);

    String Decoded_Message = readFile("compressed.txt");
//      String returntosting = toString(bitSet);


    startTime = System.currentTimeMillis();
    String d = decode(Decoded_Message);
    decompressedwritetofile(d);
    endTime = System.currentTimeMillis();
    timeElapsed = endTime - startTime;
    System.out.println("Decoded Message : " + d);
    System.out.println("Time to Decompress = " + timeElapsed + " Milliseconds");

}

        if(choice==2) {
            //Trial New
            startTime = System.currentTimeMillis();
            String s1 = readFile(filename);
            System.out.println("Message :  " + s1);
            String s2 = encode(s1);

//           FileOutputStream  stream = new  FileOutputStream("Binary.txt");
//           EncodeNode(root,stream);
//
            BitSet bitSet2 = toBinary(s2);
            compressedwritetofile(bitSet2);
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("Time to Compress  = " + timeElapsed + " Milliseconds");

            startTime = System.currentTimeMillis();
            String Compressed = readCompressedcode("compressed.txt");
            String s3 = decode(Compressed);
            decompressedwritetofile(s3);
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("Decoded Message : " + s3);
            System.out.println("Time to Decompress = " + timeElapsed + " Milliseconds");
            System.out.println("After Reading Compressed Decoded Message  :  " + s3);
        }
    }


    public static String AsciiToBinary(String asciiString){

        byte[] bytes = asciiString.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            // binary.append(' ');
        }
        return binary.toString();
    }


    private static String readMessage(String file){
        String data = "";
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    private static String readFile(String file) throws IOException {
        String data = "";

        try {
            File fileinput = new File(file);
            FileInputStream fileInputStream = new FileInputStream(fileinput);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            File testfile = new File(file);
            FileInputStream testinput = new FileInputStream(testfile);
            BufferedInputStream TestInputStream = new BufferedInputStream(testinput);
            int Rbyte = -1 ;
            int TestByte = TestInputStream.read() ;
            StringBuilder Content  = new StringBuilder();
            StringBuilder readFile = new StringBuilder();
            while ((Rbyte=bufferedInputStream.read())!=-1){
                TestByte = TestInputStream.read();
                String newByte = Integer.toBinaryString(Rbyte);
                Content.setLength(0);
                Content.append(newByte);
                Content.reverse();
                while (Content.length()<8 && TestByte != -1)
                {
                    Content.append(0);
                }
                readFile.append(Content);
            }
            return readFile.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }


    private static String readCompressedcode(String file) throws IOException {
        String data = "";

        try {
            File fileinput = new File(file);
            FileInputStream fileInputStream = new FileInputStream(fileinput);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            File testfile = new File(file);
            FileInputStream testinput = new FileInputStream(testfile);
            BufferedInputStream TestInputStream = new BufferedInputStream(testinput);
            int Rbyte = -1 ;
            int TestByte = TestInputStream.read() ;
            StringBuilder Content  = new StringBuilder();
            StringBuilder readFile = new StringBuilder();
            while ((Rbyte=bufferedInputStream.read())!=-1){
                TestByte = TestInputStream.read();
                String newByte = Integer.toBinaryString(Rbyte);
                Content.setLength(0);
                Content.append(newByte);
                Content.reverse();
                while (Content.length()<8 && TestByte != -1)
                {
                    Content.append(0);
                }
                readFile.append(Content);
            }
            return readFile.toString();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }

    private  static BitSet toBinary(String    s ){
        BitSet bits = new BitSet(s.length());
        for (int i =  0 ; i<s.length()   ; i++){
            char c = s.charAt(i);
            if (c == '0'){
                bits.set(i,false);
            }
            if (c=='1')
                bits.set(i,true);
        }
        return(bits);
    }
    private static String toString(BitSet set){
        StringBuilder s = new StringBuilder();
        for (int i =  0 ; i<set.length()   ; i++) {
            if (!set.get(i)) {
                s.append(0);
            }
            if (set.get(i)) {
                s.append(1);
            }
        }
        return s.toString();
    }

    private static void compressedwritetofile( BitSet bitSet){
        try {
            FileOutputStream myWriter = new FileOutputStream("compressed.txt");
            myWriter.write(bitSet.toByteArray());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void decompressedwritetofile(String s){
        try {
            FileWriter myWriter = new FileWriter("decompressed.txt");
            myWriter.write(s);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static HuffmanNode buildTree(Map<Character, Integer> freq) {

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();
        for (Character c : keySet) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = c;
            huffmanNode.frequency = freq.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;
            priorityQueue.offer(huffmanNode);
        }
        assert priorityQueue.size() > 0;

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '-';

            sum.left = x;

            sum.right = y;
            root = sum;

            priorityQueue.offer(sum);
        }

        return priorityQueue.poll();
    }


    private static void setPrefixCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                charPrefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                setPrefixCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setPrefixCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }

    private static String encode(String test) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < test.length(); i++) {
            if (!freq.containsKey(test.charAt(i))) {
                freq.put(test.charAt(i), 0);
            }
            freq.put(test.charAt(i), freq.get(test.charAt(i)) + 1);
        }

        System.out.println("Character Frequency Map = " + freq);
        root = buildTree(freq);

        setPrefixCodes(root, new StringBuilder());
        System.out.println("Character Prefix Map = " + charPrefixHashMap);
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            s.append(charPrefixHashMap.get(c));
        }

        return s.toString();
    }

    private static String decode(String s) {

        StringBuilder d = new StringBuilder();

        HuffmanNode temp = root;

        System.out.println("Encoded: " + s);

        for (int i = 0; i < s.length(); i++) {
            int j = parseInt(String.valueOf(s.charAt(i)));

            if (j == 0) {
                temp = temp.left;
                if (temp.left == null && temp.right == null) {
                    d.append(temp.data);
                    temp = root;
                }
            }
            if (j == 1) {
                temp = temp.right;
                if (temp.left == null && temp.right == null) {
                    d.append(temp.data);
                    temp = root;
                }
            }
        }

        return d.toString();
    }



}

class HuffmanNode implements Comparable<HuffmanNode> {
    int frequency;
    char data;
    HuffmanNode left, right;

    public int compareTo(HuffmanNode node) {
        return frequency - node.frequency;
    }
}
