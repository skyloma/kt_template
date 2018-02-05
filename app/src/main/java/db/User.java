package db;

import java.util.List;

public class User {
    public Role role;
    public String icon;
    public String name;
    public String tel;
    public int    id;
    public String avatar;
    public String department;
    public String job;
    public String pwd;
    public String email;
    public String username;

    public class Role {
        public boolean           valid;
        public String            parent;
        public String            eName;
        public List<Permissions> permissions;
        public String            name;
        public int               id;
        public List<Menus>       menus;

        public class Permissions {
            public String name;
            public String description;
            public String action;
            public int    id;
            public String category;
        }

        public class Menus {
            public List<Menus> children;
            public String  name;
            public String  icon;
            public int     id;
            public int     sort;
            public String  href;
            public String  parentId;
            public String  isShow;
        }
    }
}
