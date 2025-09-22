package FileLockManagement;

public class File {
        private String filename;
        private User lock[];
        private int Qsize;
        public File(){
                this.filename="";
                this.Qsize=0;
                this.lock=new User[50];
        }

        public String getFilename() {
                return filename;
        }

        public void setFilename(String filename) {
                this.filename = filename;
        }

        public User getLock() {
                return lock[0];
        }

        public void setLock(User u) {
                lock[Qsize++]=u;
                u.setLockedFile(this.filename);
                u.setUserStatus(u.getUserStatus()+1);
        }

        public void queueLock(User u)
        {
                lock[Qsize++]=u;
                u.setLockedFile(this.filename);
                u.setUserStatus(u.getUserStatus()+2);
        }

        public void setUnLock(User u)
        {
                User pre=lock[0];
                if(getQsize()>1) {
                        for (int i = 0; i < getQsize() - 1; i++) {
                                lock[i] = lock[i + 1];
                        }
                }
                pre.setUserStatus(pre.getUserStatus()-1);
                pre.setLockedFile(null);
                if(getQsize()!=1)
                lock[0].setUserStatus(lock[0].getUserStatus()-1);
                lock[--Qsize]=null;
        }
        public int getQsize() {
                return Qsize;
        }
}
