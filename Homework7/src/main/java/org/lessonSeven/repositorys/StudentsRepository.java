package org.lessonSeven.repositorys;

import org.lessonSeven.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student,Long> {
}
