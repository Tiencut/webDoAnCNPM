# Thiết kế Cơ sở dữ liệu

## 1. Mô hình thực thể-quan hệ (ERD)

### 1.1. Các thực thể chính

#### 1.1.1. User (Người dùng)
- **Mô tả:** Lưu trữ thông tin chung của tất cả người dùng (sinh viên, giảng viên, quản trị viên)
- **Thuộc tính:**
  - `user_id` (PK): ID duy nhất
  - `username`: Tên đăng nhập (từ HUSC)
  - `password_hash`: Mật khẩu đã mã hóa
  - `full_name`: Họ và tên
  - `email`: Email
  - `phone`: Số điện thoại
  - `role`: Vai trò (student/lecturer/admin)
  - `created_at`: Thời gian tạo
  - `updated_at`: Thời gian cập nhật

#### 1.1.2. CourseSection (Lớp học phần)
- **Mô tả:** Thông tin về các lớp học phần của môn đồ án
- **Thuộc tính:**
  - `course_section_id` (PK): ID lớp học phần
  - `name`: Tên lớp học phần (ví dụ: CNPM K45.1)
  - `academic_year`: Năm học (ví dụ: 2023-2024)
  - `semester`: Học kỳ (ví dụ: HK1)
  - `lecturer_id` (FK): Giảng viên phụ trách lớp học phần (có thể là giảng viên chính)

#### 1.1.3. Student (Sinh viên)
- **Mô tả:** Thông tin bổ sung cho sinh viên
- **Thuộc tính:**
  - `student_id` (PK): Mã sinh viên
  - `user_id` (FK): Tham chiếu đến User
  - `major`: Ngành học
  - `academic_year`: Khóa học
  - `course_section_id` (FK): Tham chiếu đến CourseSection (lớp học phần)

#### 1.1.4. Lecturer (Giảng viên)
- **Mô tả:** Thông tin bổ sung cho giảng viên
- **Thuộc tính:**
  - `lecturer_id` (PK): Mã giảng viên
  - `user_id` (FK): Tham chiếu đến User
  - `department`: Khoa
  - `phone_number`: Số điện thoại

#### 1.1.5. Group (Nhóm)
- **Mô tả:** Thông tin các nhóm sinh viên
- **Thuộc tính:**
  - `group_id` (PK): ID nhóm
  - `name`: Tên nhóm
  - `lecturer_id` (FK): Giảng viên hướng dẫn
  - `course_section_id` (FK): Tham chiếu đến CourseSection (lớp học phần)
  - `created_at`: Thời gian tạo
  - `updated_at`: Thời gian cập nhật

#### 1.1.6. Project (Đề tài)
- **Mô tả:** Thông tin các đề tài đồ án
- **Thuộc tính:**
  - `project_id` (PK): ID đề tài
  - `title`: Tên đề tài
  - `description`: Mô tả
  - `objectives`: Mục tiêu
  - `technologies`: Công nghệ sử dụng
  - `status`: Trạng thái (available/assigned/completed)
  - `created_at`: Thời gian tạo

#### 1.1.7. Report (Báo cáo)
- **Mô tả:** Báo cáo tiến độ hàng tuần
- **Thuộc tính:**
  - `report_id` (PK): ID báo cáo
  - `group_id` (FK): Nhóm
  - `week_number`: Số tuần
  - `content`: Nội dung
  - `feedback`: Phản hồi từ giảng viên
  - `created_at`: Thời gian tạo

#### 1.1.8. Notification (Thông báo)
- **Mô tả:** Các thông báo hệ thống
- **Thuộc tính:**
  - `notification_id` (PK): ID thông báo
  - `user_id` (FK): Người nhận
  - `title`: Tiêu đề
  - `content`: Nội dung
  - `is_read`: Đã đọc/chưa
  - `created_at`: Thời gian tạo

### 1.2. Các mối quan hệ

1. **User - Student:** Một User có thể là một Student (1-1)
2. **User - Lecturer:** Một User có thể là một Lecturer (1-1)
3. **Student - Group:** Một Student thuộc một Group (n-1)
4. **Group - Lecturer:** Một Group có một Lecturer hướng dẫn (n-1)
5. **Group - Project:** Một Group thực hiện một Project (1-1)
6. **Group - Report:** Một Group có nhiều Report (1-n)
7. **User - Notification:** Một User có nhiều Notification (1-n)
8. **CourseSection - Student:** Một CourseSection có nhiều Student (1-n)
9. **CourseSection - Group:** Một CourseSection có nhiều Group (1-n)

## 2. Schema cơ sở dữ liệu

-- Bảng CourseSection
CREATE TABLE course_sections (
  course_section_id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(100) NOT NULL, -- Tên lớp học phần (ví dụ: CNPM K45.1)
  academic_year VARCHAR(20) NOT NULL, -- Năm học (ví dụ: 2023-2024)
  semester VARCHAR(20) NOT NULL, -- Học kỳ (ví dụ: HK1)
  lecturer_id VARCHAR(20), -- Giảng viên phụ trách lớp học phần (có thể là giảng viên chính)
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id)
);


```sql
-- Bảng User
CREATE TABLE users (
  user_id VARCHAR(36) PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone VARCHAR(20),
  role ENUM('student', 'lecturer', 'admin') NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Bảng Student
CREATE TABLE students (
  student_id VARCHAR(20) PRIMARY KEY,
  user_id VARCHAR(36) UNIQUE NOT NULL,
  major VARCHAR(100),
  academic_year VARCHAR(20),
  course_section_id VARCHAR(36),
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  FOREIGN KEY (course_section_id) REFERENCES course_sections(course_section_id)
);

-- Bảng Lecturer
CREATE TABLE lecturers (
  lecturer_id VARCHAR(20) PRIMARY KEY,
  user_id VARCHAR(36) UNIQUE NOT NULL,
  max_groups INT DEFAULT 5,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng Group
CREATE TABLE groups (
  group_id VARCHAR(36) PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  lecturer_id VARCHAR(20),
  course_section_id VARCHAR(36),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (lecturer_id) REFERENCES lecturers(lecturer_id),
  FOREIGN KEY (course_section_id) REFERENCES course_sections(course_section_id)
);

-- Bảng Student-Group (nhiều-nhiều)
CREATE TABLE student_group (
  student_id VARCHAR(20),
  group_id VARCHAR(36),
  is_leader BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (student_id, group_id),
  FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
  FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);

-- Bảng Project
CREATE TABLE projects (
  project_id VARCHAR(36) PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  objectives TEXT,
  technologies TEXT,
  status ENUM('available', 'assigned', 'completed') DEFAULT 'available',
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bảng Group-Project (1-1)
CREATE TABLE group_project (
  group_id VARCHAR(36) PRIMARY KEY,
  project_id VARCHAR(36) UNIQUE NOT NULL,
  FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE,
  FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

-- Bảng Report
CREATE TABLE reports (
  report_id VARCHAR(36) PRIMARY KEY,
  group_id VARCHAR(36) NOT NULL,
  week_number INT NOT NULL,
  content TEXT NOT NULL,
  feedback TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (group_id) REFERENCES groups(group_id) ON DELETE CASCADE
);

-- Bảng Notification
CREATE TABLE notifications (
  notification_id VARCHAR(36) PRIMARY KEY,
  user_id VARCHAR(36) NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  is_read BOOLEAN DEFAULT FALSE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);
```

## 3. Giải thích thiết kế

1. **Tách User và Student/Lecturer:**
   - Tách biệt thông tin xác thực (User) với thông tin nghiệp vụ (Student/Lecturer)
   - Dễ dàng mở rộng các vai trò khác trong tương lai

2. **Quản lý Lớp học phần (CourseSection):**
   - Thêm bảng `course_sections` để quản lý các lớp học phần của môn đồ án.
   - Liên kết sinh viên và nhóm với lớp học phần tương ứng, cho phép quản lý nhiều lớp và nhiều nhóm trong mỗi lớp.

2. **Quan hệ Student-Group:**
   - Sử dụng bảng trung gian student_group để hỗ trợ quan hệ nhiều-nhiều
   - Có thể đánh dấu leader của nhóm

3. **Quản lý Project:**
   - Tách biệt Project với Group để dễ quản lý
   - Có thể mở rộng cho phép một Project được thực hiện bởi nhiều Group

4. **Báo cáo tiến độ:**
   - Lưu trữ lịch sử báo cáo theo tuần
   - Có thể thêm các trường như file đính kèm, điểm số

5. **Thông báo:**
   - Hỗ trợ gửi thông báo đến từng người dùng
   - Có thể mở rộng thành hệ thống thông báo đa kênh

## 4. Index và tối ưu

```sql
-- Index cho các trường thường xuyên tìm kiếm
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_students_class ON students(class);
CREATE INDEX idx_projects_status ON projects(status);
CREATE INDEX idx_notifications_user ON notifications(user_id, is_read);
```