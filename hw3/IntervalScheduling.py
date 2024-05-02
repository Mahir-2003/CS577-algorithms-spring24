
def __main__():
    instances = int(input())

    for i in range(instances):
        jobCount = int(input())
        intervals = []

        for j in range(jobCount):
            job = input().split(" ")
            intervals.append(Interval(job[0], job[1]))
        
        intervals.sort(key=lambda interval: interval.end)

        count = 1
        lastEndTime = intervals[0].end
        
        for j in range(1, len(intervals)):
            if intervals[j].start >= lastEndTime:
                count += 1
                lastEndTime = intervals[j].end
        
        print(count)

class Interval:
    def __init__(self, start, end):
        self.start = start
        self.end = end
    def __str__(self):
        return f"start: {self.start} and end: {self.end}"

if __name__ == "__main__":
    __main__()