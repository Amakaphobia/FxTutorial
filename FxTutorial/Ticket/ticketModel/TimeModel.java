package ticketModel;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;


@SuppressWarnings("javadoc")
public class TimeModel
{
	public static class Tag{
		private final static Color COLOR_TODAY = Color.GREEN;
		private final static Color COLOR_SCHOOL = Color.YELLOW;
		private final static Color COLOR_WE = Color.RED;
		private final static Color COLOR_WORK = Color.BLUE;

		private final LocalDate datum;
		private CalendarWeek week;
		private final ObjectProperty<Color> farbe = new SimpleObjectProperty<>();
		public final ObjectProperty<Color> farbeProperty() { return this.farbe; }


		private Tag(LocalDate datum) { this.datum = datum; }
		public LocalDate getDatum() { return this.datum; }
		public String getName() { return this.datum.getDayOfWeek().name(); }

		public int getWeekOfYear() {
			return
				this.datum.get(
					WeekFields.ISO.weekOfWeekBasedYear());
		}

		public Color getColor() {
			Color ret = COLOR_WORK;

			if(this.datum.equals(LocalDate.now()))
				ret = COLOR_TODAY;
			else if(this.computeSchoolDay())
				ret = COLOR_SCHOOL;
			else if(this.isWeekEnd())
				ret = COLOR_WE;

			return ret;
		}

		public void setWeek(CalendarWeek week) { this.week = week; }

		public boolean computeSchoolDay() {
			return
				this.datum.getDayOfWeek() == DayOfWeek.MONDAY
				||
				(this.datum.getDayOfWeek() == DayOfWeek.TUESDAY
				 && this.week.isEvenMonth());
		}

		public boolean isWeekEnd() { return this.datum.getDayOfWeek() == DayOfWeek.SATURDAY || this.datum.getDayOfWeek() == DayOfWeek.SUNDAY; }

		public boolean isWorkDay() { return !this.isWeekEnd() && !this.computeSchoolDay(); }

		@Override
		public String toString() {
			return this.datum.toString();
		}
	}

	public class CalendarWeek{
		private ObjectProperty<Tag> montag = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> dienstag = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> mittwoch = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> donnerstag = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> freitag = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> samstag = new SimpleObjectProperty<>();
		private ObjectProperty<Tag> sonntag = new SimpleObjectProperty<>();

		private List<Tag> tage = new ArrayList<>();
		public List<Tag> getTage() { return this.tage; }

		private boolean evenWeek;
		public boolean isEvenMonth() { return this.evenWeek; }
		public void setEvenWeek(boolean evenWeek) { this.evenWeek = evenWeek; }
		public void toggleEvenWeek() {this.evenWeek = !this.evenWeek; }

		public CalendarWeek(List<Tag> tage) {
			this.evenWeek =
					tage.stream()
						.filter(t -> t != null)
						.findFirst()
						.get()
						.getWeekOfYear()
					% 2 == 0;

			this.tage = tage;

			for(Tag e : tage) {
				DayOfWeek dayOfWeek = e.getDatum().getDayOfWeek();

				switch(dayOfWeek) {
					case MONDAY:
						this.montag.set(e);
						break;
					case TUESDAY:
						this.dienstag.set(e);
						break;
					case WEDNESDAY:
						this.mittwoch.set(e);
						break;
					case THURSDAY:
						this.donnerstag.set(e);
						break;
					case FRIDAY:
						this.freitag.set(e);
						break;
					case SATURDAY:
						this.samstag.set(e);
						break;
					case SUNDAY:
						this.sonntag.set(e);
						break;
				}
				e.setWeek(this);
				e.computeSchoolDay();
			}
		}


		public ObjectProperty<Tag> montagProperty() { return this.montag; }
		public ObjectProperty<Tag> dienstagProperty() { return this.dienstag; }
		public ObjectProperty<Tag> mittwochProperty() {return this.mittwoch; }
		public ObjectProperty<Tag> donnerstagProperty() { return this.donnerstag; }
		public ObjectProperty<Tag> freitagProperty() { return this.freitag; }
		public ObjectProperty<Tag> samstagProperty() { return this.samstag; }
		public ObjectProperty<Tag> sonntagProperty() { return this.sonntag; }

		@Override
		public String toString() {
			return String.format(
					"%s:%s:%s:%s:%s:%s:%s",
					this.montag,
					this.dienstag,
					this.mittwoch,
					this.donnerstag,
					this.freitag,
					this.samstag,
					this.sonntag);
		}
	}

	@SuppressWarnings("unused")
	private LocalDate Heute;

	private List<CalendarWeek> wochen;
	public List<CalendarWeek> getWochen() { return this.wochen; }

	public TimeModel() {
		this(LocalDate.now());
	}

	public TimeModel(LocalDate Date) {
		super();
		this.Heute = Date;
		if(Date != null)
			this.setWochenOf(Date);
	}

	public void setWochenOf(LocalDate Date) {
		this.wochen =
			IntStream.rangeClosed(1, Date.getMonth().length(Date.isLeapYear()))
				.mapToObj
					(i -> new Tag(
						LocalDate.of(
							Date.getYear(),
							Date.getMonth(),
							i)))
				.collect(Collectors.groupingBy(t -> t.getWeekOfYear()))
				.entrySet().stream()
				.sorted(Comparator.comparingInt(e -> e.getKey()))
				.map(e -> e.getValue())
				.map(l -> new CalendarWeek(l))
				.collect(Collectors.toList());
	}
}
