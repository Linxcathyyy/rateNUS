// add your descriptions here
// Format:
// {git id}: "{description}",
const ALL_DESCRIPTIONS = {
  JeffZincatz: "A not-so-hardcore gamer. Year 2 computer science undergraduate.",
  Linxcathyyy: "I like coffee, cats and rilakkuma. Computer Science Sophomore @NUS.",
  Ruofan1023: "A passionate software engineer and an aspiring technopreneur.",
  SkyBlaise99: "A regular CS god.",
  HongshuW: "Energetic computer science undergraduate from NUS with passion in exploring emerging technologies and developing software."
};

class TeamMemberDescriptions {
  getAllDescriptions() {
    return ALL_DESCRIPTIONS;
  }
}

export default new TeamMemberDescriptions();
