package converter;

import entity.Department;
import jakarta.persistence.AttributeConverter;

public class DepartmentTypeConverter
        implements AttributeConverter<Department.Type,Character > {
    @Override
    public Character convertToDatabaseColumn(Department.Type type) {
        return type.toString().charAt(0);
    }

    @Override
    public Department.Type convertToEntityAttribute(Character code) {
        if (code == 'D') {
            return Department.Type.DEV;
        }
        if (code == 'T') {
            return Department.Type.TES;
        }
        if (code == 'K') {
            return Department.Type.KES;
        }
        if (code == 'P') {
            return Department.Type.MASTER;
        }
        return Department.Type.PROJECT;
    }
}
