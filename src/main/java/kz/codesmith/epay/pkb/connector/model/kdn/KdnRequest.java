package kz.codesmith.epay.pkb.connector.model.kdn;

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
        protected LocalDate birthdate;
}
