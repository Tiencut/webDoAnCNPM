-- Placeholder for initial data
INSERT INTO users (username, password, email) VALUES ('admin', 'admin', 'admin@example.com');
INSERT INTO users (username, password, email) VALUES ('user1', 'user1', 'user1@example.com');
INSERT INTO users (username, password, email) VALUES ('user2', 'user2', 'user2@example.com');

-- Thêm dữ liệu cho bảng lecturers
INSERT INTO lecturers (lecturer_id, user_id, max_groups) VALUES ('L001', (SELECT id FROM users WHERE username = 'admin'), 5);

-- Thêm dữ liệu cho bảng course_sections
INSERT INTO course_sections (course_section_id, name, academic_year, semester, lecturer_id, created_at, updated_at) 
VALUES ('CS001', 'Lập trình Java', '2023-2024', 'HK1', 'L001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Thêm dữ liệu cho bảng groups
INSERT INTO groups (group_id, name, lecturer_id, course_section_id, created_at, updated_at) 
VALUES ('G001', 'Nhóm 1', 'L001', 'CS001', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Thêm dữ liệu cho bảng notifications
INSERT INTO notifications (notification_id, user_id, title, content, is_read, created_at) 
VALUES ('N001', (SELECT id FROM users WHERE username = 'user1'), 'Thông báo 1', 'Nội dung thông báo 1', FALSE, CURRENT_TIMESTAMP);

-- Thêm dữ liệu cho bảng projects
INSERT INTO projects (project_id, title, description, objectives, technologies, status, group_id, created_at) 
VALUES ('P001', 'Dự án quản lý sinh viên', 'Mô tả dự án quản lý sinh viên', 'Mục tiêu dự án', 'Java, Spring Boot, React', 'AVAILABLE', 'G001', CURRENT_TIMESTAMP);

-- Thêm dữ liệu cho bảng reports
INSERT INTO reports (report_id, group_id, week_number, content, feedback, created_at) 
VALUES ('R001', 'G001', 1, 'Báo cáo tuần 1', 'Phản hồi tuần 1', CURRENT_TIMESTAMP);

-- Thêm dữ liệu cho bảng students
INSERT INTO students (student_id, user_id, major, academic_year, course_section_id) 
VALUES ('S001', (SELECT id FROM users WHERE username = 'user2'), 'Công nghệ thông tin', '2023-2024', 'CS001');