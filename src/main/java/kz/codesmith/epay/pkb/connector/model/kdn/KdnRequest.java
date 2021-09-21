package kz.codesmith.epay.pkb.connector.model.kdn;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KdnRequest implements Serializable {
        protected String iin;
        protected String lastname;
        protected String firstname;
        protected String middlename;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        protected LocalDate birthdate;
}
