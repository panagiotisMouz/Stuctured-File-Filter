# Stuctured File Filter

- Stuctured File Filter allows an Analyzer to set filters in structured data files (i.e., files whose records have specific fields), and visualize the records of the files that meet their criteria filter put by the analyzer.

- A structured data file is a text file that contains records, each one of which catches a line in the file. Each record has a specific set from fields, which are separated from each other by a separating symbol. Usually this set is the comma (so we have CSV - Comma Separated Values files), or the tab (tsv - Tab Separated Values), or more rarely ‘|’. The first line of the file contains their names fields.

- The first thing the analyst does is register a file in the system, so that the system internally represents the characteristics of the file: its path in the operating system, all its fields, as well as an auxiliary name, by which we can refer to this without having to use the entire path. To to enable the parser to work with large files, it must reduce the volume of records presented to him. The analyzer then submits a filter which is the coupling of one or more individual filters. The system returns all records that meets the criteria of all individual filters and presents it to him. The analyst can ask the system to record the response in a file, to process it later. It may also ask for a bar chart or a line chart with results returned

### System Actions

- Register of structured data file
- Retrieval of information
- Query file with filter
- Print the result of a filter in a file
- Presentation of a result on a chart
