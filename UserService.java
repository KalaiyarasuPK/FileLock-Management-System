package FileLockManagement;

import java.util.Arrays;

public class UserService {
    private User users[]=new User[100];
    private int size=0;

    public void createUser(String username)
    {
        if(isUser(username))
        {
            System.out.println("Error -> Failed to create user "+username+", this user already exists");
            return;
        }
        users[size]= new User();
        users[size++].setUsername(username);
        System.out.println("User "+username+" is created successfully");
    }

    public void removeUser(String username){
        if(!isUser(username))
        {
            System.out.println("Error -> Failed to remove user "+username+", this user doesn't exists");return;
        }
        boolean f=false;
        for(int i=0;i<size;i++)
        {
            if(users[i].getUsername().equals(username))
            {
                f=true;
            }
            if(f)
            {
                if(i!=size-1)
                    users[i]=users[i+1];
                else
                {
                    users[i]=null;
                    size--;
                }
            }
        }
        System.out.println("User Removed Successfully");
    }
    public User getUser(String name)
    {
        for(int i=0;i<size;i++)
        {
            if(users[i].getUsername().equals(name))
                return users[i];
        }
        return null;
    }

    public void viewAllUser()
    {
        if(this.size==0)
        {
            System.out.println("No Users have created yet...");return;
        }
        for(int i=0;i<size;i++)
        {
            System.out.print(users[i].getUsername()+" ");
        }
        System.out.println();
    }
    public boolean isUser(String username)
    {
        for(int i=0;i<size;i++)
        {
            if(users[i].getUsername().equals(username))
                return true;
        }
        return false;
    }
    public void status(){
        if(this.size==0)
        {
            System.out.println("No users added yet");
            return;
        }
        System.out.println(this.size);
        for(int i=0;i<this.size;i++)
        {
            if(users[i].getUserStatus()==0)
            {
                System.out.println("Username : "+users[i].getUsername()+" UserStatus : No files locked");
            }
            else if(users[i].getUserStatus()==1)
            {
                    System.out.println("Username : "+users[i].getUsername()+" UserStatus : Locked Filename -> "+users[i].getLockedFile());
            }
            else
            {
                System.out.println("Username : "+users[i].getUsername()+" UserStatus : Waiting in queue for the file -> "+users[i].getLockedFile() +"no:"+users[i].getUserStatus());
            }
        }
    }
    public void reset(){
        Arrays.fill(this.users,null);
        this.size=0;
        System.out.println("Users are resetting...");
    }

}
