package lab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@Component("country")
public class SimpleCountry implements Country {
    long id;
    String name;
    String codeName;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCodeName() {
        return this.codeName;
    }

    public SimpleCountry setId(long id) {
        this.id = id;
        return this;
    }

    public SimpleCountry setName(String name) {
        this.name = name;
        return this;
    }

    public SimpleCountry setCodeName(String codeName) {
        this.codeName = codeName;
        return this;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SimpleCountry)) return false;
        final SimpleCountry other = (SimpleCountry) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$codeName = this.getCodeName();
        final Object other$codeName = other.getCodeName();
        if (this$codeName == null ? other$codeName != null : !this$codeName.equals(other$codeName)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $codeName = this.getCodeName();
        result = result * PRIME + ($codeName == null ? 43 : $codeName.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof SimpleCountry;
    }

    public String toString() {
        return "SimpleCountry(id=" + this.getId() + ", name=" + this.getName() + ", codeName=" + this.getCodeName() + ")";
    }
}
