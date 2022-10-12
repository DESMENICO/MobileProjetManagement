package be.helha.desmette.mobileprojetmanagement.db;

public abstract class DbSchema {

    public static final class StudentTable{
        public static final String NAME = "student";
        public static final class cols {
            public static final String UUID = "uuid";
            public static final String FirstName = "firstName";
            public static final String ProjectList = "projectList";
        }
    }

    public static final class ProjectTable{
        public static final String NAME = "project";
        public static final class cols {
            public static final String Name = "name";
            public static final String description = "firstName";
            public static final String UUID = "uuid";
            public static final String Owner = "owner";
        }
    }

    public static final class StepTable{
        public static final String NAME = "step";
        public static final class cols {
            public static final String Name = "name";
            public static final String cotation = "cotation";
            public static final String Owner = "project";
        }
    }


}
