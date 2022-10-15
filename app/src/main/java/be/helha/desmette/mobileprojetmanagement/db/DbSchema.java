package be.helha.desmette.mobileprojetmanagement.db;

import java.io.Serializable;

public abstract class DbSchema implements Serializable {

    public static final class StudentTable{
        public static final String NAME = "student";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String FirstName = "firstName";
        }
    }

    public static final class ProjectTable{
        public static final String NAME = "project";
        public static final class cols {
            public static final String Name = "name";
            public static final String Description = "description";
            public static final String UUID = "uuid";
            public static final String Average = "average";
            public static final String OwnerID = "ownerId";
        }
    }

    public static final class StepTable{
        public static final String NAME = "step";
        public static final class cols {
            public static final String Name = "name";
            public static final String Cotation = "cotation";
            public static final String UUID = "uuid";
            public static final String ProjectID = "projectId";
        }
    }


}
