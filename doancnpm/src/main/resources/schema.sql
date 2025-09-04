CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE lecturers (
    lecturer_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    max_groups INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE course_sections (
    course_section_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    academic_year VARCHAR(255) NOT NULL,
    semester VARCHAR(255) NOT NULL,
    lecturer_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id)
);

CREATE TABLE groups (
    group_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lecturer_id VARCHAR(255) NOT NULL,
    course_section_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id),
    FOREIGN KEY (course_section_id) REFERENCES course_sections(course_section_id)
);

CREATE TABLE notifications (
    notification_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    is_read BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE projects (
    project_id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    objectives TEXT NOT NULL,
    technologies VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    group_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups(group_id)
);

CREATE TABLE reports (
    report_id VARCHAR(255) PRIMARY KEY,
    group_id VARCHAR(255) NOT NULL,
    week_number INT NOT NULL,
    content TEXT NOT NULL,
    feedback TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups(group_id)
);

CREATE TABLE students (
    student_id VARCHAR(255) PRIMARY KEY,
    user_id INT NOT NULL,
    major VARCHAR(255) NOT NULL,
    academic_year VARCHAR(255) NOT NULL,
    course_section_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (course_section_id) REFERENCES course_sections(course_section_id)
);