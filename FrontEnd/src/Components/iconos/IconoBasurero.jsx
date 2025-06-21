const IconoBasurero = ({ size = 24, color = "white" }) => (
  <svg width={size} height={size} viewBox="0 0 100 100"
       xmlns="http://www.w3.org/2000/svg"
       fill="none" stroke={color} strokeWidth="4" strokeLinecap="round" strokeLinejoin="round">
    <rect x="25" y="10" width="50" height="12" rx="2" />
    <rect x="20" y="22" width="60" height="60" rx="4" />
    <line x1="35" y1="30" x2="35" y2="70" />
    <line x1="50" y1="30" x2="50" y2="70" />
    <line x1="65" y1="30" x2="65" y2="70" />
  </svg>
);

export default IconoBasurero
