class Solution:
    def findDuplicate(self, paths: List[str]) -> List[List[str]]:
        if not paths or len(paths) == 0:
            return list()
        
        res = list()
        file_dict = collections.defaultdict(list)
        
        for path in paths:
            p_dir, *files = path.split(' ')
            for file in files:
                file_name, content = file.split('(')
                file_dict[content].append(p_dir + '/' + file_name)
        
        for _, file_paths in file_dict.items():
            if len(file_paths) > 1: # add more than 2 files which has same content into res
                res.append(file_paths)
                
        return res
