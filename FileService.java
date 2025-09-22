package FileLockManagement;

import java.util.Arrays;

public class FileService {
    private File files[]=new File[100];
    private int size=0;
    public void createFile(String filename) {
        if(isFile(filename))
        {
            System.out.println("Error -> Failed to create file "+filename+", this file already exists");
            return;
        }
        files[size] =new File() ;
        files[size++].setFilename(filename);
        System.out.println("File "+filename+" is created successfully");
    }

    public boolean isFile(String filename)
    {
        for(int i=0;i<size;i++)
        {
            if(files[i].getFilename().equals(filename))
                return true;
        }
        return false;
    }
    public File getFile(String filename)
    {
        for(int i=0;i<size;i++)
        {
            if(files[i].getFilename().equals(filename))
                return files[i];
        }
        return null;
    }

    public void removeFile(String filename){
        if(!isFile(filename))
        {
            System.out.println("Error -> Failed to remove file "+filename+", this file doesn't exists");return;
        }
        boolean f=false;
        for(int i=0;i<size;i++)
        {
            if(files[i].getFilename().equals(filename))
            {
                f=true;
            }
            if(f)
            {
                if(i!=size-1)
                    files[i]=files[i+1];
                else
                {
                    files[i]=null;
                    size--;
                }
            }
        }
        System.out.println("File Removed Successfully");
    }

    public void viewAllFiles() {
        if(this.size==0)
        {
            System.out.println("No files have created yet...");return;
        }
        System.out.println("All files in the system :");
        for (int i = 0; i < size; i++) {
            System.out.print(files[i].getFilename() + " ");
        }
        System.out.println();
    }
    public void createLock(String filename,String username,UserService s)
    {
        if(!isFile(filename) || !s.isUser(username))
        {
            System.out.println("Invalid username or filename");
            return;
        }
        File curf=getFile(filename);
        User curu=s.getUser(username);
        //ERROR CHECK
        if(curu.getUserStatus()!=0)
        {
            if(curu.getUserStatus()==1)
                System.out.println("Error -> Access denied,since "+curu.getUsername()+" already locked a file "+curu.getLockedFile());
            else
                System.out.println("Error -> Access denied, "+curu.getUsername()+" is queued for the file"+curu.getLockedFile());
            return;
        }
        if(curf.getQsize()==0)
        {
            curf.setLock(curu);
            System.out.println(curu.getUsername()+" locked the file "+curf.getFilename());
        }
        else
        {
            curf.queueLock(curu);
            System.out.println("Error -> Access denied, "+curu.getUsername()+" queued for the file "+curf.getFilename()+" because the file is locked by "+curf.getLock().getUsername());
        }

    }
    public void unLock(String filename,UserService s)
    {
        if(!isFile(filename))
        {
            System.out.println("Invalid username or filename");
            return;
        }
        File curf=getFile(filename);
        if(curf.getLock()==null)
        {
            System.out.println("Error -> Nobody locked the file "+curf.getFilename());
            return;
        }
        User curu=s.getUser(curf.getLock().getUsername());
        System.out.println(curf.getFilename()+" file is unlocked from "+curu.getUsername());
        curf.setUnLock(curu);
        if(curf.getQsize()>0)
        System.out.println(" and lock is given to "+curf.getLock().getUsername());
        else
            System.out.println(curf.getFilename()+" is now set free anyone can access ");
    }
    public void reset(){
        Arrays.fill(this.files,null);
        this.size=0;
        System.out.println("Files are resetting...");
    }
}
