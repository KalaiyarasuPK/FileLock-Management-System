package FileLockManagement;

import java.util.Scanner;

public class FileLock {
    public void start()
    {
        Scanner o=new Scanner(System.in);
                UserService s=new UserService();
                FileService f=new FileService();
        while(true)
        {
            String s1[]=o.nextLine().split(" ");
            if(s1[0].equals("VIEWALLFILES"))
                f.viewAllFiles();
            else if(s1[0].equals("VIEWALLUSERS"))
                s.viewAllUser();
            else if(s1[0].equals("CREATE") && s1[1].equals("USER") )
            {
                s.createUser(s1[2]);
            }
            else if(s1[0].equals("REMOVE") && s1[1].equals("USER") )
            {
                s.removeUser(s1[2]);
            }
            else if(s1[0].equals("CREATE") && s1[1].equals("FILE") )
            {
                f.createFile(s1[2]);
            }
            else if(s1[0].equals("REMOVE") && s1[1].equals("FILE") )
            {
                f.removeFile(s1[2]);
            }
            else if (s1[0].equals("LOCK")) {
                f.createLock(s1[1],s1[2],s);
            }
            else if (s1[0].equals("UNLOCK")) {
                f.unLock(s1[1],s);
            }else if(s1[0].equals("RESET")){
                s.reset();
                f.reset();
                System.out.println("System resetted successfully...");
            }else if(s1[0].equals("STATUS")){
                s.status();
            } else if (s1[0].equals("EXIT")) {
                break;
            } else
                System.out.println("Invalid command Pls retry...");
        }
    }

}
